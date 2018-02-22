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
package com.qushuwang.qushuwang.presenter.contract;


import com.qushuwang.qushuwang.base.BaseContract;
import com.qushuwang.qushuwang.bean.Apk_Update;
import com.qushuwang.qushuwang.bean.FenleiLeimuBean;
import com.qushuwang.qushuwang.bean.Meinvha_dir_List;

import java.io.File;
import java.util.List;

public interface MainContract {

    interface View extends BaseContract.BaseView {

        void Fetch_Meinvha_dir_List_Success(List<Meinvha_dir_List.DataBean> dataBean);
        void Apk_Update_Success(Apk_Update.DataBean data);
        void Apk_Update_Path_Success(File file);
        void  JouspTest_Success(List<FenleiLeimuBean>  data);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        void Fetch_Meinvha_dir_List();
        void Apk_Update();
        void Apk_Update_Path();
        void Jousp_Test();
    }
}
