/**
 * Copyright 2016 JustWayward Team
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wengmengfan.doutu.presenter.impl;


import com.blankj.utilcode.utils.RegexUtils;
import com.wengmengfan.doutu.api.Api;
import com.wengmengfan.doutu.base.RxPresenter;
import com.wengmengfan.doutu.bean.NewestBean;
import com.wengmengfan.doutu.presenter.contract.HotHomeContract;
import com.wengmengfan.doutu.presenter.contract.NewestHomeContract;
import com.wengmengfan.doutu.utils.DeviceUtils;
import com.wengmengfan.doutu.utils.RandomUtils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HotHomePresenter extends RxPresenter<HotHomeContract.View> implements HotHomeContract.Presenter<HotHomeContract.View> {

    private Api bookApi;
    public static boolean isLastSyncUpdateed = false;

    @Inject
    public HotHomePresenter(Api bookApi) {
        this.bookApi = bookApi;
    }



    @Override
    public void Fetch_HotInfo(final String Url) {
        Observable.create(new Observable.OnSubscribe<List<NewestBean>>() {
            @Override
            public void call(Subscriber<? super List<NewestBean>> subscriber) {
                //在call方法中执行异步任务
                List<NewestBean> newestBeanArrayList = new ArrayList<>();
                try {

                    Connection connect = Jsoup.connect(Url);
                    Map<String, String> header = new HashMap<String, String>();

                    header.put("User-Agent", RandomUtils.getAgentString());
                    header.put("Accept", "	text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
                    header.put("Accept-Language", "zh-cn,zh;q=0.5");
                    header.put("Accept-Charset", "	GB2312,utf-8;q=0.7,*;q=0.7");
                    Connection data = connect.data(header);
                    Document doc = data.get();

                    Elements elements = doc.select("div.col-md-12");
                    String html = elements.html();
                    Elements elementss  =  Jsoup.parse(html).select("div.soresult");
                    String htmls = elementss.html();
                    Elements document = Jsoup.parse(htmls).getElementsByTag("img");

                    for (Element e : document) {
                        NewestBean newestBean = new NewestBean();
                        newestBean.setImgUrl(e.select("img").attr("src"));
                        newestBean.setImgTitle(e.select("img").attr("title"));
                        newestBeanArrayList.add(newestBean);
                    }

                } catch (Exception e) {
                    //注意：如果异步任务中需要抛出异常，在执行结果中处理异常。需要将异常转化未RuntimException
                    throw new RuntimeException(e);
                }
                //调用subscriber#onNext方法将执行结果返回
                subscriber.onNext(newestBeanArrayList);
                //调用subscriber#onCompleted方法完成异步任务
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())//指定异步任务在IO线程运行
                .observeOn(AndroidSchedulers.mainThread())//制定执行结果在主线程运行
                .subscribe(new Observer<List<NewestBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<NewestBean> data) {
                        if (data != null && mView != null) {
                            mView.Fetch_HotInfo_Success(data);
                        }
                    }
                });
    }

    @Override
    public void downloadPicFromNet(final String imgUrl ) {
        Subscription rxSubscription = bookApi.downloadPicFromNet(imgUrl).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.toString());
                    }

                    @Override
                    public void onNext(Response<ResponseBody> data) {
                        boolean isJpg = RegexUtils.isMatch("^http(.*)\\.jpg$", imgUrl);
                        String destFileName;
                        try {
                            if(isJpg)
                                 destFileName = System.currentTimeMillis() + ".jpg";
                            else
                                destFileName = System.currentTimeMillis() + ".gif";
                            File file= saveFile(data,destFileName);
                            mView.downloadPicFromNet_Success(file.getPath());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        addSubscrebe(rxSubscription);
    }


    public File saveFile(Response<ResponseBody> response ,String destFileName) throws Exception {
        String  destFileDir = DeviceUtils.getSDPath();
        InputStream in = null;
        FileOutputStream out = null;
        byte[] buf = new byte[2048*10];
        int len;
        try {
            File dir = new File(destFileDir);
            if (!dir.exists()) {// 如果文件不存在新建一个
                dir.mkdirs();
            }
            in = response.body().byteStream();
            File file = new File(dir,destFileName);
            out = new FileOutputStream(file);
            while ((len = in.read(buf)) != -1){
                out.write(buf,0,len);
            }
            return file;
        }catch (Exception e){
            e.toString();
        }finally {
            in.close();
            out.close();
        }
        return null;
    }
}
