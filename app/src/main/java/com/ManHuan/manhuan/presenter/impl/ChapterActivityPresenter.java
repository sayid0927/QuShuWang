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
import com.ManHuan.manhuan.bean.BookInfoBean;
import com.ManHuan.manhuan.presenter.contract.ChapterContract;

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

public class ChapterActivityPresenter extends RxPresenter<ChapterContract.View> implements ChapterContract.Presenter<ChapterContract.View> {

    private Api bookApi;
    public static boolean isLastSyncUpdateed = false;

    @Inject
    public ChapterActivityPresenter(Api bookApi) {
        this.bookApi = bookApi;
    }

    @Override
    public void Fetch_BookInfo(final String Url) {

        Observable.create(new Observable.OnSubscribe<BookInfoBean>() {
            @Override
            public void call(Subscriber<? super BookInfoBean> subscriber) {
                //在call方法中执行异步任务
                BookInfoBean bookInfoBean = new BookInfoBean();
                List<BookInfoBean.BookChapterBean> bookChapterBeanList = new ArrayList<>();
                try {
                    Document doc = Jsoup.connect(Url).get();
                    Elements elements = doc.select("div.mhinfo");
                    Elements cont = doc.select("div.mh_cont_u_zj");
                    String html = elements.html();
                    String contHtml = cont.html();
                    Elements document = Jsoup.parse(html).getElementsByTag("li");
                    Elements contDocument = Jsoup.parse(contHtml).getElementsByTag("li");

                    for (Element e : contDocument) {
                        BookInfoBean.BookChapterBean bookChapterBean = new BookInfoBean.BookChapterBean();
                        bookChapterBean.setUrl(e.select("a").attr("href"));
                        bookChapterBean.setNum(e.select("a").text());
                        bookChapterBeanList.add(bookChapterBean);
                    }
                    bookInfoBean.setBookChapterBeanList(bookChapterBeanList);
                    boolean biaoqian = true;
                    for (int i = 0; i < document.size(); i++) {
                        if (document.get(i).hasClass("fengmian"))
                            bookInfoBean.setImgUrl(document.get(i).select("img").attr("src"));
                        if (document.get(i).hasClass("mingcheng"))
                            bookInfoBean.setBookName(document.get(i).select("li.mingcheng").text());
                        if (document.get(i).hasClass("zuozhe"))
                            bookInfoBean.setZuoZhe(document.get(i).select("li.zuozhe").text());
                        if (biaoqian && document.get(i).hasClass("biaoqian")) {
                            bookInfoBean.setBiaoQian(document.get(i).select("li.biaoqian").text());
                            biaoqian = false;
                        }
                        if (document.get(i).hasClass("renqi"))
                            bookInfoBean.setRenqi(document.get(i).select("li.renqi").text());
                    }
                } catch (Exception e) {
                    //注意：如果异步任务中需要抛出异常，在执行结果中处理异常。需要将异常转化未RuntimException
                    throw new RuntimeException(e);
                }
                //调用subscriber#onNext方法将执行结果返回
                subscriber.onNext(bookInfoBean);
                //调用subscriber#onCompleted方法完成异步任务
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())//指定异步任务在IO线程运行
                .observeOn(AndroidSchedulers.mainThread())//制定执行结果在主线程运行
                .subscribe(new Observer<BookInfoBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BookInfoBean data) {
                        if (data != null && mView != null) {
                            mView.Fetch_BookInfo_Success(data);
                        }
                    }
                });
    }
}
