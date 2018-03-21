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
package com.wengmengfan.btwang.component;


import com.wengmengfan.btwang.ui.activity.AboutActivity;
import com.wengmengfan.btwang.ui.activity.DetailsActivity;
import com.wengmengfan.btwang.ui.activity.FeedbackActivity;
import com.wengmengfan.btwang.ui.activity.MainActivity;
import com.wengmengfan.btwang.ui.activity.MoreActivity;
import com.wengmengfan.btwang.ui.activity.ViewBoxActivity;
import com.wengmengfan.btwang.ui.fragment.DownRankingFragment;
import com.wengmengfan.btwang.ui.fragment.HomeFragment;
import com.wengmengfan.btwang.ui.fragment.MeFragment;
import com.wengmengfan.btwang.ui.fragment.homeChildFragment.HotsFilmFragment;
import com.wengmengfan.btwang.ui.fragment.homeChildFragment.HotsMangaFragment;
import com.wengmengfan.btwang.ui.fragment.homeChildFragment.HotsTeleplayFragment;
import com.wengmengfan.btwang.ui.fragment.homeChildFragment.HotsVarietyFragment;

import dagger.Component;


@Component(dependencies = AppComponent.class)
public interface MainComponent {

    MainActivity inject(MainActivity activity);

   DownRankingFragment inject(DownRankingFragment downRankingFragment);

   HomeFragment inject(HomeFragment homeFragment);

   ViewBoxActivity inject(ViewBoxActivity viewBoxActivity);

   MeFragment inject(MeFragment meFragment);

   AboutActivity inject(AboutActivity aboutActivity);

   FeedbackActivity inject(FeedbackActivity feedbackActivity);

   HotsFilmFragment inject (HotsFilmFragment hotsFilmFragment);

   HotsMangaFragment inject (HotsMangaFragment mangaFragment);

   HotsTeleplayFragment inject (HotsTeleplayFragment teleplayFragment);

   HotsVarietyFragment inject (HotsVarietyFragment varietyFragment);

   DetailsActivity inject (DetailsActivity detailsActivity);

   MoreActivity inject (MoreActivity moreActivity);


}