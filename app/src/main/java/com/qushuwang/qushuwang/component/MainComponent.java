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
package com.qushuwang.qushuwang.component;


import android.app.Activity;

import com.qushuwang.qushuwang.ui.activity.ChapterActivity;
import com.qushuwang.qushuwang.ui.activity.ImgContentActivity;
import com.qushuwang.qushuwang.ui.activity.MainActivity;
import com.qushuwang.qushuwang.ui.activity.MhContentActivity;
import com.qushuwang.qushuwang.ui.fragment.ImageBrowseFragment;
import com.qushuwang.qushuwang.ui.fragment.ManHuanHomeFragment;
import com.qushuwang.qushuwang.ui.fragment.TuPianHomeFragment;
import com.qushuwang.qushuwang.ui.fragment.TuPian_Title;

import dagger.Component;

;

@Component(dependencies = AppComponent.class)
public interface MainComponent {

    MainActivity inject(MainActivity activity);

    ImgContentActivity inject(ImgContentActivity activity);

    ImageBrowseFragment inject (ImageBrowseFragment fragment);

    ChapterActivity inject(ChapterActivity chapterActivity );

    MhContentActivity inject(MhContentActivity mhContentActivity );

    ManHuanHomeFragment inject(ManHuanHomeFragment manHuanHomeFragment);

    TuPianHomeFragment inject(TuPianHomeFragment tuPianHomeFragment);

    TuPian_Title inject(TuPian_Title tuPian_title);

}