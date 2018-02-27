package com.qushuwang.qushuwang.ui.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.qushuwang.qushuwang.R;
import com.qushuwang.qushuwang.base.BaseActivity;
import com.qushuwang.qushuwang.base.BaseFragmentPageAdapter;
import com.qushuwang.qushuwang.bean.Apk_Update;
import com.qushuwang.qushuwang.bean.FenleiLeimuBean;
import com.qushuwang.qushuwang.bean.Meinvha_dir_List;
import com.qushuwang.qushuwang.component.AppComponent;
import com.qushuwang.qushuwang.component.DaggerMainComponent;
import com.qushuwang.qushuwang.presenter.contract.MainContract;
import com.qushuwang.qushuwang.presenter.impl.MainActivityPresenter;
import com.qushuwang.qushuwang.service.DownLoadService;
import com.qushuwang.qushuwang.ui.fragment.ManHuanHomeFragment;
import com.qushuwang.qushuwang.ui.fragment.Meinvha_Title;
import com.qushuwang.qushuwang.ui.fragment.TuPianHomeFragment;
import com.qushuwang.qushuwang.ui.fragment.WoHomeFragment;
import com.qushuwang.qushuwang.utils.DeviceUtils;
import com.qushuwang.qushuwang.utils.UmengUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainContract.View{

    @Inject
    MainActivityPresenter mPresenter;


    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.vp)
    ViewPager vp;


    private BaseFragmentPageAdapter myAdapter;

    private ArrayList<String> mTitleList = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();


    public  static  int FileSize;
    public  static  String Apk_Name;


    private ManHuanHomeFragment  manHuanHomeFragment;
    private TuPianHomeFragment tuPianHomeFragment;
    private WoHomeFragment woHomeFragmentd;




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

        UmengUtil.onEvent(MainActivity.this, "MainActivity",null);

        mTitleList.add("漫画");
        mTitleList.add("图片");
        mTitleList.add("我的");

        manHuanHomeFragment  = new ManHuanHomeFragment();
        tuPianHomeFragment = new TuPianHomeFragment();
        woHomeFragmentd = new WoHomeFragment();

        mFragments.add(manHuanHomeFragment);
        mFragments.add(tuPianHomeFragment);
        mFragments.add(woHomeFragmentd);

        myAdapter = new BaseFragmentPageAdapter(getSupportFragmentManager(), mFragments, mTitleList);
        vp.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        tabLayout.setupWithViewPager(vp);

//        mPresenter.Apk_Update();

    }


    @Override
    public void Apk_Update_Success(Apk_Update.DataBean dataBean) {
        FileSize = dataBean.getFileSize();
        Apk_Name =dataBean.getApk_Name();
        String version_info = dataBean.getUpdate_Info() ;         //更新提示信息
        int mVersion_code = DeviceUtils.getVersionCode(MainActivity.this);// 当前的版本号
        int nVersion_code = dataBean.getVersionCode();            // 服务器上的版本号

        if (mVersion_code < nVersion_code) {
            // 显示提示对话
            showNoticeDialog(version_info);
        }else {
        }
    }

    @Override
    public void Apk_Update_Path_Success(File file) {
        Uri uri = Uri.fromFile(file);
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        install.setDataAndType(uri, "application/vnd.android.package-archive");
        // 执行意图进行安装
        this.startActivity(install);
    }

    @Override
    public void showError(String message) {

    }

    /**
     * 显示更新对话框
     *
     * @param version_info
     */
    private void showNoticeDialog(String version_info) {
        // 构造对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("更新提示");
        builder.setMessage(version_info);
        // 更新
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                startService(new Intent(MainActivity.this, DownLoadService.class));
//              mPresenter.Apk_Update_Path();
            }
        });
        // 稍后更新
        builder.setNegativeButton("以后更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog noticeDialog = builder.create();
        noticeDialog.show();
    }
}
