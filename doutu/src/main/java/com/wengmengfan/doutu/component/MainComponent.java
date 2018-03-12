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
package com.wengmengfan.doutu.component;



import com.wengmengfan.doutu.ui.activity.ChapterActivity;
import com.wengmengfan.doutu.ui.activity.DongTuImgContentActivity;
import com.wengmengfan.doutu.ui.activity.ImgContentActivity;
import com.wengmengfan.doutu.ui.activity.MainActivity;
import com.wengmengfan.doutu.ui.activity.MhContentActivity;
import com.wengmengfan.doutu.ui.activity.TuPianImgContentActivity;
import com.wengmengfan.doutu.ui.fragment.DongTuHomeFragment;
import com.wengmengfan.doutu.ui.fragment.DongTuImageBrowseFragment;
import com.wengmengfan.doutu.ui.fragment.DongTu_Title;
import com.wengmengfan.doutu.ui.fragment.HotHomeFragment;
import com.wengmengfan.doutu.ui.fragment.ImageBrowseFragment;
import com.wengmengfan.doutu.ui.fragment.ManHuanHomeFragment;
import com.wengmengfan.doutu.ui.fragment.Meinvha_Title;
import com.wengmengfan.doutu.ui.fragment.NewestHomeFragment;
import com.wengmengfan.doutu.ui.fragment.SettingHomeFragment;
import com.wengmengfan.doutu.ui.fragment.TuPianHomeFragment;
import com.wengmengfan.doutu.ui.fragment.TuPian_Title;

import dagger.Component;

;

@Component(dependencies = AppComponent.class)
public interface MainComponent {

    MainActivity inject(MainActivity activity);

    ImgContentActivity inject(ImgContentActivity activity);

    ImageBrowseFragment inject(ImageBrowseFragment fragment);

    ChapterActivity inject(ChapterActivity chapterActivity);

    MhContentActivity inject(MhContentActivity mhContentActivity);

    ManHuanHomeFragment inject(ManHuanHomeFragment manHuanHomeFragment);

    TuPianHomeFragment inject(TuPianHomeFragment tuPianHomeFragment);

    TuPian_Title inject(TuPian_Title tuPian_title);

    Meinvha_Title inject(Meinvha_Title fragment);

    TuPianImgContentActivity inject(TuPianImgContentActivity tuPianImgContentActivity);

    DongTuHomeFragment inject(DongTuHomeFragment woHomeFragment);

    DongTu_Title inject(DongTu_Title dongTu_title);

    DongTuImgContentActivity inject(DongTuImgContentActivity dongTuImgContentActivity);

    DongTuImageBrowseFragment inject(DongTuImageBrowseFragment dongTuImageBrowseFragment);

    SettingHomeFragment inject(SettingHomeFragment settingHomeFragment);




    NewestHomeFragment inject(NewestHomeFragment newestHomeFragment);

    HotHomeFragment inject(HotHomeFragment hotHomeFragment);

}