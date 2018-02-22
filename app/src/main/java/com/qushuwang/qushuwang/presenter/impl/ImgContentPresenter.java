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
package com.qushuwang.qushuwang.presenter.impl;

import com.qushuwang.qushuwang.api.Api;
import com.qushuwang.qushuwang.base.RxPresenter;
import com.qushuwang.qushuwang.bean.ImgContent;
import com.qushuwang.qushuwang.bean.request.Meinvha_Title_request;
import com.qushuwang.qushuwang.presenter.contract.ImgContentContract;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ImgContentPresenter extends RxPresenter<ImgContentContract.View> implements ImgContentContract.Presenter<ImgContentContract.View> {

    private Api bookApi;
    public static boolean isLastSyncUpdateed = false;

    @Inject
    public ImgContentPresenter(Api bookApi) {
        this.bookApi = bookApi;
    }


    @Override
    public void Fetch_ImgContent_List(Meinvha_Title_request request) {

        Subscription rxSubscription = bookApi.Fetch_ImgContent_List(request).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImgContent>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.toString());
                    }

                    @Override
                    public void onNext(ImgContent data) {
                        if (data != null && mView != null && data.getRes().equals("00000")) {
                            List<ImgContent.DataBean> dataBean = data.getData();
                            mView.Fetch_ImgContent_List_Success(dataBean);

                        }
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
