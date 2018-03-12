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
import com.wengmengfan.doutu.bean.DongTuHomeBean;
import com.wengmengfan.doutu.presenter.contract.DongTuHomeContract;

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

public class DongTuHomePresenter extends RxPresenter<DongTuHomeContract.View> implements DongTuHomeContract.Presenter<DongTuHomeContract.View> {

    private Api bookApi;
    public static boolean isLastSyncUpdateed = false;

    @Inject
    public DongTuHomePresenter(Api bookApi) {
        this.bookApi = bookApi;
    }


    @Override
    public void DongTu_Title(final String Url) {
        Observable.create(new Observable.OnSubscribe<List<DongTuHomeBean>>() {
            @Override
            public void call(Subscriber<? super List<DongTuHomeBean>> subscriber) {
                //在call方法中执行异步任务
                List<DongTuHomeBean> dongTuHomeBeanList = new ArrayList<>();
                try {
                    Document doc = Jsoup.connect(Url).get();
                    Elements menu = doc.select("h2.title");

                    for (Element e : menu) {
                        DongTuHomeBean dongTuHomeBean = new DongTuHomeBean();
                        dongTuHomeBean.setUrl(e.select("a").attr("href"));
                        dongTuHomeBean.setTitle(e.select("a").text());
                        if (dongTuHomeBean.getTitle() != null && dongTuHomeBean.getUrl() != null &&
                              !dongTuHomeBean.getTitle().equals("")  && !dongTuHomeBean.getUrl().equals("") )
                            dongTuHomeBeanList.add(dongTuHomeBean);

                    }
                } catch (Exception e) {
                    //注意：如果异步任务中需要抛出异常，在执行结果中处理异常。需要将异常转化未RuntimException
                    throw new RuntimeException(e);
                }
                //调用subscriber#onNext方法将执行结果返回
                subscriber.onNext(dongTuHomeBeanList);
                //调用subscriber#onCompleted方法完成异步任务
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())//指定异步任务在IO线程运行
                .observeOn(AndroidSchedulers.mainThread())//制定执行结果在主线程运行
                .subscribe(new Observer<List<DongTuHomeBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<DongTuHomeBean> data) {
                        if (data != null && mView != null) {
                            mView.DongTu_Title_Success(data);
                        }
                    }
                });
    }
}
