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

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;


public interface ApiService {

    /**
     * 下载 图片
     */
    @GET
    Observable<Response<ResponseBody>> downloadPicFromNet(@Url String imgUrl);


    /**
     * 获取UrlPath列表集合
     */
    @POST(Constant.MEINVHA_IMG_LIST)
    Observable<ImgContent> Fetch_ImgContent_List(@Body Meinvha_Title_request request);


    /**
     * 获取标题列表集合
     */
    @POST(Constant.MEINVHA_TITLE_LIST)
    Observable<Meinvha_Title> Fetch_Meinvha_Title_List(@Body Meinvha_Title_request request);

    /**
     * 获取目录列表集合
     */
    @POST(Constant.MEINVHA_DIR_LIST)
    Observable<Meinvha_dir_List> Fetch_Meinvha_dir_List();

    /**
     * 获取APP更新信息
     */
    @POST(Constant.APK_UPDATE)
    Observable<Apk_Update> Fetch_Apk_Update();


    /**
     * 下载apk
     */
    @GET(Constant.APK_UPDATE_PATH)
    Observable<Response<ResponseBody>> Fetch_Apk_Update_Path();



}