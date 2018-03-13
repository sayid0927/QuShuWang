package com.ManHuan.manhuan.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.ManHuan.manhuan.ui.fragment.SettingHomeFragment;
import com.pgyersdk.update.PgyUpdateManager;
import com.ManHuan.manhuan.R;
import com.ManHuan.manhuan.base.BaseActivity;
import com.ManHuan.manhuan.base.BaseFragmentPageAdapter;
import com.ManHuan.manhuan.component.AppComponent;
import com.ManHuan.manhuan.component.DaggerMainComponent;
import com.ManHuan.manhuan.presenter.contract.MainContract;
import com.ManHuan.manhuan.presenter.impl.MainActivityPresenter;
import com.ManHuan.manhuan.ui.fragment.DongTuHomeFragment;
import com.ManHuan.manhuan.ui.fragment.ManHuanHomeFragment;
import com.ManHuan.manhuan.ui.fragment.TuPianHomeFragment;
import com.ManHuan.manhuan.utils.UmengUtil;

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


    private ManHuanHomeFragment manHuanHomeFragment;
    private TuPianHomeFragment tuPianHomeFragment;
    private DongTuHomeFragment dongTuHomeFragment;

    private SettingHomeFragment settingHomeFragment;

    public  static MainActivity mainActivity;


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
        UmengUtil.onEvent( "MainActivity");


        mTitleList.add("漫画");
        mTitleList.add("设置");
//        mTitleList.add("动图");

        manHuanHomeFragment = new ManHuanHomeFragment();
        settingHomeFragment = new SettingHomeFragment();
//        tuPianHomeFragment = new TuPianHomeFragment();
//        dongTuHomeFragment = new DongTuHomeFragment();

        mFragments.add(manHuanHomeFragment);
        mFragments.add(settingHomeFragment);
//    mFragments.add(tuPianHomeFragment);
//     mFragments.add(dongTuHomeFragment);

        myAdapter = new BaseFragmentPageAdapter(getSupportFragmentManager(), mFragments, mTitleList);
        vp.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        tabLayout.setupWithViewPager(vp);

        PgyUpdateManager.setIsForced(true); //设置是否强制更新。true为强制更新；false为不强制更新（默认值）。
        PgyUpdateManager.register(this);
//    mPresenter.Apk_Update();
        mainActivity=this;

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void killAll() {
        super.killAll();
    }
}
