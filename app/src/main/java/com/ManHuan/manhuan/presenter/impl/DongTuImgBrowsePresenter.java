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
package com.ManHuan.manhuan.presenter.impl;

import com.ManHuan.manhuan.api.Api;
import com.ManHuan.manhuan.base.RxPresenter;
import com.ManHuan.manhuan.presenter.contract.DongTuImgBrowseContract;
import com.ManHuan.manhuan.utils.DeviceUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DongTuImgBrowsePresenter extends RxPresenter<DongTuImgBrowseContract.View> implements DongTuImgBrowseContract.Presenter<DongTuImgBrowseContract.View> {

    private Api bookApi;
    public static boolean isLastSyncUpdateed = false;

    @Inject
    public DongTuImgBrowsePresenter(Api bookApi) {
        this.bookApi = bookApi;
    }

    @Override
    public void downloadPicFromNet(String imgUrl) {
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
                        try {
                            File  file= saveFile(data);
                            mView.downloadPicFromNet_Success(file.getPath());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void Fetch_DongTu_Img(final String url) {

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //在call方法中执行异步任务
                String ImgUrl = null;
                try {
                    Document doc = Jsoup.connect(url).get();
                    Elements elements = doc.select("div.ny");
                    for (Element e : elements) {
                        ImgUrl =  e.select("img").attr("src");
                    }
                } catch (Exception e) {
                    //注意：如果异步任务中需要抛出异常，在执行结果中处理异常。需要将异常转化未RuntimException
                    throw new RuntimeException(e);
                }
                //调用subscriber#onNext方法将执行结果返回
                subscriber.onNext(ImgUrl);
                //调用subscriber#onCompleted方法完成异步任务
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())//指定异步任务在IO线程运行
                .observeOn(AndroidSchedulers.mainThread())//制定执行结果在主线程运行
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String data) {
                        if (data != null && mView != null) {
                            mView.Fetch_DongTu_Img_Success(data);
                        }
                    }
                });
    }


    private  String destFileName = System.currentTimeMillis() + ".gif";

    public File saveFile(Response<ResponseBody> response) throws Exception {
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
