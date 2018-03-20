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
package com.wengmengfan.btwang.presenter.impl;

import com.orhanobut.logger.Logger;
import com.wengmengfan.btwang.api.Api;
import com.wengmengfan.btwang.base.RxPresenter;
import com.wengmengfan.btwang.bean.HomeInfoBean;
import com.wengmengfan.btwang.presenter.contract.HomeContract;
import com.wengmengfan.btwang.utils.RandomUtils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeFragmentPresenter extends RxPresenter<HomeContract.View> implements HomeContract.Presenter<HomeContract.View> {

    private Api api;
    public static boolean isLastSyncUpdateed = false;

    @Inject
    public HomeFragmentPresenter(Api api) {
        this.api = api;
    }


    @Override
    public void Fetch_80sHomeInfo(final String Url) {
        Observable.create(new Observable.OnSubscribe<HomeInfoBean>() {

            @Override
            public void call(Subscriber<? super HomeInfoBean> subscriber) {
                //在call方法中执行异步任务
                HomeInfoBean homeInfoBean = new HomeInfoBean();
                List<HomeInfoBean.HotsInfoBean> hotsInfoBeanList = new ArrayList<>();
                List<HomeInfoBean.ColTitleBean> colTitleBeanList = new ArrayList<>();
                List<HomeInfoBean.SectionBean> sectionBeans = new ArrayList<>();
                try {
                    Connection connect = Jsoup.connect(Url);
                    Map<String, String> header = new HashMap<>();
                    header.put("User-Agent", RandomUtils.getAgentString());
                    header.put("Accept", "	text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
                    header.put("Accept-Language", "zh-cn,zh;q=0.5");
                    header.put("Accept-Charset", "	GB2312,utf-8;q=0.7,*;q=0.7");
                    Connection data = connect.data(header);
                    Document doc = data.get();
                    Elements elHots = doc.select("div.hots");
                    Elements elTile = doc.select("div.title");
                    Elements elFilm = doc.select("div.sl2");

                    for (Element ef : elFilm) {
                        HomeInfoBean.SectionBean sectionBean = new HomeInfoBean.SectionBean();
                        String href = ef.select("a").attr("href");
                        sectionBean.setHerf(href);
                        Elements docPoster = ef.select("div.list_mov_poster");
                        for (Element dp: docPoster){
                           String imgUrl =    dp.select("img").attr("data-original");
                           sectionBean.setImgUrl(imgUrl);
                            Elements span =dp.select("span.corner");
                            for (Element c : span) {
                                String sclass = c.select("span").attr("class");
                                String sa = c.select("span").text();
                                if (sclass.equals("corner score")) {
                                     sectionBean.setScore(sa);
                                } else {
                                    sectionBean.setLanguage(sa);
                                }
                            }
//                            hotsBean.setEm(docTitle.get(poster).select("em").text());
//                            hotsBean.setHerf(docTitle.get(poster).select("a").attr("href"));
//                            hotsBean.setTitle(docTitle.get(poster).select("a").text());
                        }
                        Logger.e("VVVV");

                    }


                    for (Element et : elTile) {
                        HomeInfoBean.ColTitleBean colTitleBean = new HomeInfoBean.ColTitleBean();
                        String href = et.select("a").attr("href");
                        String txt = et.select("a").text();
                        String span = et.select("span").text();
                        if (!href.equals("") && !txt.equals("") && !span.equals("")) {
                            colTitleBean.setHrefUrl(href);
                            colTitleBean.setTitle(txt);
                            colTitleBean.setCountPop(span);
                            colTitleBeanList.add(colTitleBean);
                        }
                    }
                    for (Element e : elHots) {
                        String id = e.select("div").attr("id");
                        String type = null;
                        switch (id) {
                            case "hot_1":
                                type = "电影";
                                break;
                            case "hot_9":
                                type = "电视剧";
                                break;
                            case "hot_4":
                                type = "综艺";
                                break;
                            case "hot_14":
                                type = "动漫";
                                break;
                        }
                        String html = e.html();
                        Document docHtml = Jsoup.parse(html);
                        Elements docPoster = docHtml.select("div.list_mov_poster");
                        Elements docTitle = docHtml.select("div.list_mov_title");
                        for (int poster = 0; poster < docPoster.size(); poster++) {
                            HomeInfoBean.HotsInfoBean hotsBean = new HomeInfoBean.HotsInfoBean();
                            hotsBean.setType(type);
                            hotsBean.setImgUrl(docPoster.get(poster).select("img").attr("data-original"));
                            Elements span = docPoster.get(poster).select("span.corner");
                            for (Element c : span) {
                                String sclass = c.select("span").attr("class");
                                String sa = c.select("span").text();
                                if (sclass.equals("corner score")) {
                                    hotsBean.setScore(sa);
                                } else {
                                    hotsBean.setLanguage(sa);
                                }
                            }
                            hotsBean.setEm(docTitle.get(poster).select("em").text());
                            hotsBean.setHerf(docTitle.get(poster).select("a").attr("href"));
                            hotsBean.setTitle(docTitle.get(poster).select("a").text());
                            hotsInfoBeanList.add(hotsBean);
                        }
                        homeInfoBean.setColTitleBean(colTitleBeanList);
                        homeInfoBean.setHotsInfoBeans(hotsInfoBeanList);
                    }
                } catch (Exception e) {
                    //注意：如果异步任务中需要抛出异常，在执行结果中处理异常。需要将异常转化未RuntimException
                    throw new RuntimeException(e);
                }
                //调用subscriber#onNext方法将执行结果返回
                subscriber.onNext(homeInfoBean);
                //调用subscriber#onCompleted方法完成异步任务
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())//指定异步任务在IO线程运行
                .observeOn(AndroidSchedulers.mainThread())//制定执行结果在主线程运行
                .subscribe(new Observer<HomeInfoBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView != null)
                            mView.showError("网络异常");
                    }

                    @Override
                    public void onNext(HomeInfoBean data) {
                        if (data != null && mView != null) {
                            mView.Fetch_80sHomeInfo_Success(data);
                        }
                    }
                });
    }
}
