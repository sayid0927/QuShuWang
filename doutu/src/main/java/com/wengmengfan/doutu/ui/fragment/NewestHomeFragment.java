package com.wengmengfan.doutu.ui.fragment;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.blankj.utilcode.utils.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.wengmengfan.doutu.R;
import com.wengmengfan.doutu.base.BaseFragment;
import com.wengmengfan.doutu.base.Constant;
import com.wengmengfan.doutu.bean.NewestBean;
import com.wengmengfan.doutu.component.AppComponent;
import com.wengmengfan.doutu.component.DaggerMainComponent;
import com.wengmengfan.doutu.presenter.contract.NewestHomeContract;
import com.wengmengfan.doutu.presenter.impl.NewestHomePresenter;
import com.wengmengfan.doutu.ui.adapter.Newest_Home_Adapter;
import com.wengmengfan.doutu.view.MyLoadMoreView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * sayid ....
 * Created by wengmf on 2018/3/9.
 */

public class NewestHomeFragment extends BaseFragment implements NewestHomeContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @Inject
    NewestHomePresenter mPresenter;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.srl_android)
    SwipeRefreshLayout srlAndroid;


    private Newest_Home_Adapter mAdapter;
    private List<NewestBean> dataBean;

    private boolean isRefresh = false;

    private int Start_Page = 1;

    private String imgUrl;

    public   static  NewestHomeFragment newestHomeFragment;

    @Override
    public void loadData() {
        mPresenter.Fetch_NewestInfo("http://www.lubiaoqing.com/news.html");

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_newest_home;
    }

    @Override
    protected void initView(Bundle bundle) {
        super.initView(bundle);

        mAdapter = new Newest_Home_Adapter(dataBean, getSupportActivity());
        mAdapter.setOnLoadMoreListener(NewestHomeFragment.this, rvList);
        mAdapter.setLoadMoreView(new MyLoadMoreView());
        srlAndroid.setOnRefreshListener(this);
        rvList.setLayoutManager(new GridLayoutManager(getSupportActivity(), 2));
        rvList.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new Newest_Home_Adapter.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClickListener(final NewestBean item) {
                Logger.e(" item>>>  " + item.getImgUrl());
                imgUrl = item.getImgUrl();
                String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE};
                if (!EasyPermissions.hasPermissions(getSupportActivity(), perms)) {
                    EasyPermissions.requestPermissions(getSupportActivity(), "需要读写权限",
                            1000, perms);
                } else
                    mPresenter.downloadPicFromNet(item.getImgUrl());
            }
        });
        newestHomeFragment = this;
    }

    @Override
    public void attachView() {
        mPresenter.attachView(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.detachView();
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void onLoadMoreRequested() {
        if (Start_Page < 1000) {
            Start_Page += 1;
            mPresenter.Fetch_NewestInfo("http://www.lubiaoqing.com/news/" + String.valueOf(Start_Page) + ".html");
            srlAndroid.setEnabled(false);
        }
    }

    @Override
    public void onRefresh() {
        Start_Page = 1;
        isRefresh = true;
        mAdapter.setEnableLoadMore(false);
        mPresenter.Fetch_NewestInfo("http://www.lubiaoqing.com/news.html");
    }

    @Override
    public void Fetch_NewestInfo_Success(List<NewestBean> dataBean) {
        if (isRefresh) {
            srlAndroid.setRefreshing(false);
            mAdapter.setEnableLoadMore(true);
            isRefresh = false;
            mAdapter.setNewData(dataBean);
        } else {
            setState(Constant.STATE_SUCCESS);
            srlAndroid.setEnabled(true);
            mAdapter.addData(dataBean);
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void downloadPicFromNet_Success(String filePath) {
        Logger.e(filePath);
        Toast.makeText(getSupportActivity(), "已保存图片在  " + filePath, Toast.LENGTH_LONG).show();
    }


    public void onPermissionsDenied() {
        ToastUtils.showLongToast("没有权限无法下载图片");
    }

    public void onPermissionsGranted() {
        mPresenter.downloadPicFromNet(imgUrl);
    }

}
