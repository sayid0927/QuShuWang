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

import com.wengmengfan.btwang.api.Api;
import com.wengmengfan.btwang.base.RxPresenter;
import com.wengmengfan.btwang.bean.DownRaningBean;
import com.wengmengfan.btwang.presenter.contract.DownRankingContract;
import com.wengmengfan.btwang.presenter.contract.HotsFilmContract;
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

public class HotsFilmPresenter extends RxPresenter<HotsFilmContract.View> implements HotsFilmContract.Presenter<HotsFilmContract.View> {


    private Api Api;
    public static boolean isLastSyncUpdateed = false;

    @Inject
    public HotsFilmPresenter(Api Api) {
        this.Api = Api;
    }


}
