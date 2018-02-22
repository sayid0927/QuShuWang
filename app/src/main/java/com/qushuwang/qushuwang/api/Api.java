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
package com.qushuwang.qushuwang.api;


import com.qushuwang.qushuwang.base.Constant;
import com.qushuwang.qushuwang.bean.Apk_Update;
import com.qushuwang.qushuwang.bean.ImgContent;
import com.qushuwang.qushuwang.bean.Meinvha_Title;
import com.qushuwang.qushuwang.bean.Meinvha_dir_List;
import com.qushuwang.qushuwang.bean.request.Meinvha_Title_request;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class Api {

    public static Api instance;

    private ApiService service;

    public Api(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(okHttpClient)
                .build();
        service = retrofit.create(ApiService.class);
    }

    public static Api getInstance(OkHttpClient okHttpClient) {
        if (instance == null)
            instance = new Api(okHttpClient);
        return instance;
    }

    public Observable<Response<ResponseBody>> downloadPicFromNet(String imgUrl) {
        return service.downloadPicFromNet(imgUrl);
    }


    public Observable<ImgContent> Fetch_ImgContent_List(Meinvha_Title_request request) {
        return service.Fetch_ImgContent_List(request);
    }



    public Observable<Meinvha_Title> Fetch_Meinvha_Title_List(Meinvha_Title_request request) {
        return service.Fetch_Meinvha_Title_List(request);
    }

    public Observable<Meinvha_dir_List> Fetch_ManHuan_List() {
        return service.Fetch_Meinvha_dir_List();
    }

    public Observable<Apk_Update> Fetch_Apk_Update() {
        return service.Fetch_Apk_Update();
    }

    public Observable<Response<ResponseBody>> Fetch_Apk_Update_Path() {
        return service.Fetch_Apk_Update_Path();
    }

}
