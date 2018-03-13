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
import com.ManHuan.manhuan.bean.FenleiLeimuBean;
import com.ManHuan.manhuan.presenter.contract.ManHuanHomeContract;
import com.ManHuan.manhuan.utils.DeviceUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ManHuanHomePresenter extends RxPresenter<ManHuanHomeContract.View> implements ManHuanHomeContract.Presenter<ManHuanHomeContract.View> {

    private Api bookApi;
    public static boolean isLastSyncUpdateed = false;

    @Inject
    public ManHuanHomePresenter(Api bookApi) {
        this.bookApi = bookApi;
    }

    @Override
    public void Jousp_Test() {

        Observable.create(new Observable.OnSubscribe< List<FenleiLeimuBean>>() {
            @Override
            public void call(Subscriber<? super  List<FenleiLeimuBean>>subscriber) {
                //在call方法中执行异步任务
                List<FenleiLeimuBean> fenleiLeimuBeanList = new ArrayList<>();
                try {
                    Document doc = Jsoup.connect("http://m.kuman.com/all/").get();
                    Elements manhua = doc.select("div.swiper-slide");

                    for(int i=0;i<manhua.size();i++){
                        FenleiLeimuBean fenleiLeimuBean = new FenleiLeimuBean();

                        fenleiLeimuBean.setLeimu(manhua.get(i).select("a").text());
                        fenleiLeimuBean.setUrl(manhua.get(i).select("a").attr("href"));
                        fenleiLeimuBean.setId(i);
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
                .subscribe(new Observer<List<FenleiLeimuBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<FenleiLeimuBean> data) {
                        if (data != null && mView != null ) {
                            mView.JouspTest_Success(data);
                        }
                    }
                });
    }
}
