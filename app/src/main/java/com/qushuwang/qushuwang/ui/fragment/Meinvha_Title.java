package com.qushuwang.qushuwang.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.utils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.qushuwang.qushuwang.R;
import com.qushuwang.qushuwang.base.BaseFragment;
import com.qushuwang.qushuwang.bean.FenleiImgBean;
import com.qushuwang.qushuwang.bean.request.Meinvha_Title_request;
import com.qushuwang.qushuwang.component.AppComponent;
import com.qushuwang.qushuwang.component.adapter.DaggerMeinvha_TitleComponent;
import com.qushuwang.qushuwang.presenter.contract.Meinvha_TitleContract;
import com.qushuwang.qushuwang.presenter.impl.Meinvha_TitlePresenter;
import com.qushuwang.qushuwang.ui.activity.ChapterActivity;
import com.qushuwang.qushuwang.ui.activity.ImgContentActivity;
import com.qushuwang.qushuwang.ui.adapter.Meinvha_Title_Adapter;
import com.qushuwang.qushuwang.view.MyLoadMoreView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class Meinvha_Title extends BaseFragment implements Meinvha_TitleContract.View, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    Meinvha_TitlePresenter mPresenter;

    @BindView(R.id.title_list)
    RecyclerView Book_Dir_List;

    @BindView(R.id.srl_android)
    SwipeRefreshLayout srlAndroid;

    private Meinvha_Title_Adapter mAdapter;
    private List<FenleiImgBean> dataBean;

    private boolean isRefresh = false;
    private int id;
    private String Url;
    private int Start_Page = 1;
    private int End_Page = 1;

    public static Meinvha_Title newInstance(int id, String url) {
        Meinvha_Title manHuan_name = new Meinvha_Title();
        Bundle bundle = new Bundle();
        bundle.putInt("Id", id);
        bundle.putString("Url", url);
        manHuan_name.setArguments(bundle);
        return manHuan_name;
    }


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_meinvha_dir;
    }


    @Override
    protected void initView(Bundle bundle) {
        id = bundle.getInt("Id");
        Url = bundle.getString("Url");
        String lastIndex=  Url.substring(Url.lastIndexOf("/"),Url.length());

        if(lastIndex.equals("/")){
            Url = Url.substring(0,Url.length() - 1);
        }

        mPresenter.Fetch_Fenlei_Img(Url+"-"+Start_Page+"-"+End_Page+"//");
        mAdapter = new Meinvha_Title_Adapter(dataBean, getSupportActivity());
        mAdapter.setOnLoadMoreListener(Meinvha_Title.this, Book_Dir_List);
        mAdapter.setLoadMoreView(new MyLoadMoreView());
        srlAndroid.setOnRefreshListener(this);
        Book_Dir_List.setLayoutManager(new GridLayoutManager(getSupportActivity(), 2));
        Book_Dir_List.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new Meinvha_Title_Adapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(FenleiImgBean item) {
                Intent intent = new Intent(getActivity(), ChapterActivity.class);
                intent.putExtra("Url",item.getUrl());
                intent.putExtra("ImgUrl",item.getImgUrl());
                intent.putExtra("BookName",item.getBookName());
                intent.putExtra("BookNum",item.getBookNum());
                startActivity(intent);

            }
        });
    }

    @Override
    public void attachView() {
        mPresenter.attachView(this);

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMeinvha_TitleComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public void showError(String message) {
        LogUtils.e(message);
    }


    @Override
    public void Fetch_Fenlei_Img_Success(List<FenleiImgBean> dataBean) {
        if (isRefresh) {
            srlAndroid.setRefreshing(false);
            mAdapter.setEnableLoadMore(true);
            isRefresh = false;
            mAdapter.setNewData(dataBean);
        } else {
            srlAndroid.setEnabled(true);
            mAdapter.addData(dataBean);
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onLoadMoreRequested() {
        Start_Page += 1;
        mPresenter.Fetch_Fenlei_Img(Url+"-"+Start_Page+"-"+End_Page+"//");
        srlAndroid.setEnabled(false);
    }

    @Override
    public void onRefresh() {
        Start_Page = 1;
        isRefresh = true;
        mAdapter.setEnableLoadMore(false);
        mPresenter.Fetch_Fenlei_Img(Url+"-"+Start_Page+"-"+End_Page+"//");
    }
}
