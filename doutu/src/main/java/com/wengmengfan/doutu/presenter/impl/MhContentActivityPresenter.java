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


import com.wengmengfan.doutu.api.Api;
import com.wengmengfan.doutu.base.RxPresenter;
import com.wengmengfan.doutu.bean.MhContentBean;
import com.wengmengfan.doutu.presenter.contract.MhContentContract;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MhContentActivityPresenter extends RxPresenter<MhContentContract.View> implements MhContentContract.Presenter<MhContentContract.View> {

    private Api bookApi;
    public static boolean isLastSyncUpdateed = false;

    @Inject
    public MhContentActivityPresenter(Api bookApi) {
        this.bookApi = bookApi;
    }


    @Override
    public void Fetch_ImgInfo(final String Url) {

        Observable.create(new Observable.OnSubscribe<List<MhContentBean>>() {
            @Override
            public void call(Subscriber<? super List<MhContentBean>> subscriber) {
                //在call方法中执行异步任务
                List<MhContentBean> mhContentBeanList = new ArrayList<>();
                try {
                    Document doc = Jsoup.connect(Url).get();
                    Elements elements = doc.select("div.cont-wp");

                    String html = elements.html();
                    Elements document = Jsoup.parse(html).getElementsByTag("li");

                    for (Element e : document) {
                        MhContentBean mhContentBean = new MhContentBean();
                        mhContentBean.setType("ManHuan");
                        mhContentBean.setImgSrc(e.select("img").attr("src"));
                        mhContentBean.setDataSrc(e.select("img").attr("data-src"));
                        mhContentBean.setDataImageId(e.select("img").attr("data-image_id"));
                        mhContentBeanList.add(mhContentBean);
                    }
                } catch (Exception e) {
                    //注意：如果异步任务中需要抛出异常，在执行结果中处理异常。需要将异常转化未RuntimException
                    throw new RuntimeException(e);
                }
                //调用subscriber#onNext方法将执行结果返回
                subscriber.onNext(mhContentBeanList);
                //调用subscriber#onCompleted方法完成异步任务
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())//指定异步任务在IO线程运行
                .observeOn(AndroidSchedulers.mainThread())//制定执行结果在主线程运行
                .subscribe(new Observer<List<MhContentBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<MhContentBean> data) {
                        if (data != null && mView != null) {
                            mView.Fetch_ImgInfo_Success(data);
                        }
                    }
                });
    }

    @Override
    public void Fetch_TuPian_ImgInfo_Success(final String ImgUrl,final  String Url) {
        Observable.create(new Observable.OnSubscribe<List<MhContentBean>>() {
            @Override
            public void call(Subscriber<? super List<MhContentBean>> subscriber) {
                //在call方法中执行异步任务
                List<MhContentBean> mhContentBeanList = new ArrayList<>();
                try {
                    Document doc = Jsoup.connect(ImgUrl).get();
                    Elements elements = doc.select("div[id=pages]");

                    String html = elements.html();
                    Elements document = Jsoup.parse(html).getElementsByTag("a");

                    for (Element e : document) {
                        MhContentBean mhContentBean = new MhContentBean();
                        String url = e.select("a").attr("href");
                         if(url.endsWith("html")){

                             mhContentBean.setImgSrc(Url+ e.select("a").attr("href"));
                             mhContentBean.setType("TuPian");
                             mhContentBeanList.add(mhContentBean);

                         }
                    }
                } catch (Exception e) {
                    //注意：如果异步任务中需要抛出异常，在执行结果中处理异常。需要将异常转化未RuntimException
                    throw new RuntimeException(e);
                }
                //调用subscriber#onNext方法将执行结果返回
                subscriber.onNext(mhContentBeanList);
                //调用subscriber#onCompleted方法完成异步任务
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())//指定异步任务在IO线程运行
                .observeOn(AndroidSchedulers.mainThread())//制定执行结果在主线程运行
                .subscribe(new Observer<List<MhContentBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<MhContentBean> data) {
                        if (data != null && mView != null) {
                            mView.Fetch_TuPian_ImgInfo_Success(data);
                        }
                    }
                });
    }
}
