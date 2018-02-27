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
import com.qushuwang.qushuwang.bean.BookInfoBean;
import com.qushuwang.qushuwang.bean.MhContentBean;
import com.qushuwang.qushuwang.bean.TuPianHomeBean;

import java.util.List;

public interface MhContentContract {

    interface View extends BaseContract.BaseView {

        void Fetch_ImgInfo_Success( List<MhContentBean> data );

        void Fetch_TuPian_ImgInfo_Success( List<MhContentBean> data );
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void Fetch_ImgInfo(String   Url );
        void Fetch_TuPian_ImgInfo_Success(String   ImgUrl,String Url );
    }
}
