package com.wengmengfan.doutu.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.blankj.utilcode.utils.RegexUtils;
import com.orhanobut.logger.Logger;
import com.pgyersdk.update.PgyUpdateManager;
import com.wengmengfan.doutu.R;
import com.wengmengfan.doutu.base.BaseActivity;
import com.wengmengfan.doutu.base.BaseFragmentPageAdapter;
import com.wengmengfan.doutu.component.AppComponent;
import com.wengmengfan.doutu.component.DaggerMainComponent;
import com.wengmengfan.doutu.presenter.contract.MainContract;
import com.wengmengfan.doutu.presenter.impl.MainActivityPresenter;
import com.wengmengfan.doutu.presenter.impl.NewestHomePresenter;
import com.wengmengfan.doutu.ui.fragment.DongTuHomeFragment;
import com.wengmengfan.doutu.ui.fragment.HotHomeFragment;
import com.wengmengfan.doutu.ui.fragment.ManHuanHomeFragment;
import com.wengmengfan.doutu.ui.fragment.NewestHomeFragment;
import com.wengmengfan.doutu.ui.fragment.SettingHomeFragment;
import com.wengmengfan.doutu.ui.fragment.TuPianHomeFragment;
import com.wengmengfan.doutu.utils.UmengUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActivity implements MainContract.View , EasyPermissions.PermissionCallbacks {

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


    private NewestHomeFragment newestHomeFragment;
    private HotHomeFragment hotHomeFragment;
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

        mTitleList.add("最新");
        mTitleList.add("热门");
        mTitleList.add("设置");
//        mTitleList.add("动图");

        newestHomeFragment = new NewestHomeFragment();
        hotHomeFragment = new HotHomeFragment();
        settingHomeFragment = new SettingHomeFragment();
//        tuPianHomeFragment = new TuPianHomeFragment();
//        dongTuHomeFragment = new DongTuHomeFragment();

        mFragments.add(newestHomeFragment);
        mFragments.add(hotHomeFragment);
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


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        NewestHomeFragment.newestHomeFragment.onPermissionsGranted();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        switch (requestCode){
            case 1000:
                NewestHomeFragment.newestHomeFragment.onPermissionsDenied();
                break;
        }
    }
}
