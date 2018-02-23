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

import com.blankj.utilcode.utils.LogUtils;
import com.qushuwang.qushuwang.api.Api;
import com.qushuwang.qushuwang.base.RxPresenter;
import com.qushuwang.qushuwang.bean.Apk_Update;
import com.qushuwang.qushuwang.bean.FenleiLeimuBean;
import com.qushuwang.qushuwang.bean.Meinvha_dir_List;
import com.qushuwang.qushuwang.presenter.contract.MainContract;
import com.qushuwang.qushuwang.utils.DeviceUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivityPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter<MainContract.View> {

    private Api bookApi;
    public static boolean isLastSyncUpdateed = false;

    @Inject
    public MainActivityPresenter(Api bookApi) {
        this.bookApi = bookApi;
    }




    @Override
    public void Apk_Update() {
        Subscription rxSubscription = bookApi.Fetch_Apk_Update().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Apk_Update>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(e.toString());
                    }

                    @Override
                    public void onNext(Apk_Update data) {
                        if (data != null && mView != null && data.getRes().equals("00000")) {
                            Apk_Update.DataBean dataBean = data.getData();
                            mView.Apk_Update_Success(dataBean);

                        }
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void Apk_Update_Path() {
        Subscription rxSubscription = bookApi.Fetch_Apk_Update_Path().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.toString());
                    }

                    @Override
                    public void onNext(Response<ResponseBody> data) {
                        try {
                            File file = saveFile(data);
                            mView.Apk_Update_Path_Success(file);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        addSubscrebe(rxSubscription);

    }




    private String destFileName = System.currentTimeMillis() + ".apk";

    public File saveFile(Response<ResponseBody> response) throws Exception {

        String destFileDir = DeviceUtils.getSDPath();
        InputStream in = null;
        FileOutputStream out = null;
        byte[] buf = new byte[1024];
        int len;
        try {
            File dir = new File(destFileDir);
            if (!dir.exists()) {// 如果文件不存在新建一个
                dir.mkdirs();
            }
            in = response.body().byteStream();

            File file = new File(dir, destFileName);
            out = new FileOutputStream(file);
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            return file;
        } catch (Exception e) {
            e.toString();

        } finally {
            in.close();
            out.close();
        }
        return null;
    }
}
