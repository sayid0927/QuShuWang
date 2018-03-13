package com.wengmengfan.btwang.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.blankj.utilcode.utils.RegexUtils;
import com.orhanobut.logger.Logger;
import com.pgyersdk.update.PgyUpdateManager;
import com.wengmengfan.btwang.R;
import com.wengmengfan.btwang.base.BaseActivity;
import com.wengmengfan.btwang.base.BaseFragmentPageAdapter;
import com.wengmengfan.btwang.component.AppComponent;
import com.wengmengfan.btwang.component.DaggerMainComponent;
import com.wengmengfan.btwang.presenter.contract.MainContract;
import com.wengmengfan.btwang.presenter.impl.MainActivityPresenter;
import com.wengmengfan.btwang.ui.fragment.DownRankingFragment;
import com.wengmengfan.btwang.ui.fragment.FilmFragment;
import com.wengmengfan.btwang.ui.fragment.HotFilmFragment;
import com.wengmengfan.btwang.utils.UmengUtil;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    MainActivityPresenter mPresenter;


    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.vp)
    ViewPager vp;


    private BaseFragmentPageAdapter myAdapter;

    private ArrayList<String> mTitleList = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();


    public static int FileSize;
    public static String Apk_Name;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void attachView() {
        mPresenter.attachView(this);
    }

    @Override
    public void detachView() {
        mPresenter.detachView();
    }

    @Override
    public void initView() {

        UmengUtil.onEvent("MainActivity");

        mTitleList.add("电影下载排行");
        mTitleList.add("本月热门电影");
        mTitleList.add("电影");

        DownRankingFragment downRankingFragment = new DownRankingFragment();
        HotFilmFragment hotFilmFragment = new HotFilmFragment();
        FilmFragment filmFragment = new FilmFragment();

        mFragments.add(downRankingFragment);
        mFragments.add(hotFilmFragment);
        mFragments.add(filmFragment);

        myAdapter = new BaseFragmentPageAdapter(getSupportFragmentManager(), mFragments, mTitleList);
        vp.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        tabLayout.setupWithViewPager(vp);

        PgyUpdateManager.setIsForced(true); //设置是否强制更新。true为强制更新；false为不强制更新（默认值）。
        PgyUpdateManager.register(this);


    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void killAll() {
        super.killAll();
    }
}
