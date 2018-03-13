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
import com.ManHuan.manhuan.bean.FenleiImgBean;
import com.ManHuan.manhuan.presenter.contract.Meinvha_TitleContract;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Meinvha_TitlePresenter extends RxPresenter<Meinvha_TitleContract.View> implements Meinvha_TitleContract.Presenter<Meinvha_TitleContract.View> {


    private Api bookApi;
    public static boolean isLastSyncUpdateed = false;

    @Inject
    public Meinvha_TitlePresenter(Api bookApi) {
        this.bookApi = bookApi;
    }

    @Override
    public void Fetch_Fenlei_Img(final String url) {

        Observable.create(new Observable.OnSubscribe< List<FenleiImgBean>>() {

            @Override
            public void call(Subscriber<? super  List<FenleiImgBean>> subscriber) {
                //在call方法中执行异步任务
                List<FenleiImgBean> fenleiLeimuBeanList = new ArrayList<>();

                try {

                    Document doc = Jsoup.connect(url).get();
                    Elements manhua = doc.select("div.fenlei_img");
                    String html = manhua.html();
                    Elements document = Jsoup.parse(html).getElementsByTag("li");

                    for(int i=0;i<document.size();i++){

                        FenleiImgBean fenleiLeimuBean = new FenleiImgBean();
                        fenleiLeimuBean.setImgUrl(document.get(i).select("img").attr("src"));
                        fenleiLeimuBean.setUrl(document.get(i).select("a").attr("href"));
                        fenleiLeimuBean.setBookName(document.get(i).select("p").text());
                        fenleiLeimuBean.setBookNum(document.get(i).select("b").text());
                        fenleiLeimuBeanList.add(fenleiLeimuBean);

                    }
                } catch (Exception e) {
                    //注意：如果异步任务中需要抛出异常，在执行结果中处理异常。需要将异常转化未RuntimException
                    throw new RuntimeException(e);
                }
                //调用subscriber#onNext方法将执行结果返回
                subscriber.onNext(fenleiLeimuBeanList);
                //调用subscriber#onCompleted方法完成异步任务
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())//指定异步任务在IO线程运行
           .observeOn(AndroidSchedulers.mainThread())//制定执行结果在主线程运行
           .subscribe(new Observer<List<FenleiImgBean>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<FenleiImgBean> data) {
                        if (data != null && mView != null ) {
                            mView.Fetch_Fenlei_Img_Success(data);
                        }
                    }
                });
    }
}
