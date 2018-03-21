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
import com.wengmengfan.btwang.presenter.contract.MainContract;
import com.wengmengfan.btwang.presenter.contract.MoreActivityContract;
import com.wengmengfan.btwang.utils.DeviceUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class MoreActivityPresenter extends RxPresenter<MoreActivityContract.View> implements MoreActivityContract.Presenter<MoreActivityContract.View> {

    private Api bookApi;
    public static boolean isLastSyncUpdateed = false;

    @Inject
    public MoreActivityPresenter(Api bookApi) {
        this.bookApi = bookApi;
    }



}
