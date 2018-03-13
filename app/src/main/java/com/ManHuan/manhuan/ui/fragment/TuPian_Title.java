package com.ManHuan.manhuan.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.utils.LogUtils;
import com.ManHuan.manhuan.R;
import com.ManHuan.manhuan.base.BaseFragment;
import com.ManHuan.manhuan.base.Constant;
import com.ManHuan.manhuan.bean.TuPianHomeBean;
import com.ManHuan.manhuan.component.AppComponent;
import com.ManHuan.manhuan.component.DaggerAppComponent;
import com.ManHuan.manhuan.component.DaggerMainComponent;
import com.ManHuan.manhuan.component.adapter.DaggerMeinvha_TitleComponent;
import com.ManHuan.manhuan.presenter.contract.TuPian_TitleContract;
import com.ManHuan.manhuan.presenter.impl.TuPian_TitlePresenter;
import com.ManHuan.manhuan.ui.activity.TuPianImgContentActivity;
import com.ManHuan.manhuan.ui.adapter.TuPian_Home_Adapter;
import com.ManHuan.manhuan.utils.StringUtlis;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class TuPian_Title extends BaseFragment implements TuPian_TitleContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    TuPian_TitlePresenter mPresenter;

    @BindView(R.id.title_list)
    RecyclerView Book_Dir_List;

    @BindView(R.id.srl_android)
    SwipeRefreshLayout srlAndroid;

    private TuPian_Home_Adapter mAdapter;
    private List<TuPianHomeBean> dataBean;

    private int id;
    private String Url;

    private boolean isRefresh = false;

    public static TuPian_Title newInstance(int id, String url) {
        TuPian_Title manHuan_name = new TuPian_Title();
        Bundle bundle = new Bundle();
        bundle.putInt("Id", id);
        bundle.putString("Url", url);
        manHuan_name.setArguments(bundle);
        return manHuan_name;
    }


    @Override
    public void loadData() {
//        mPresenter.Fetch_TuPian_Img(Url);
        setState(Constant.STATE_SUCCESS);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_meinvha_dir;
    }

    @Override
    protected void initView(Bundle bundle) {
        id = bundle.getInt("Id");
        Url = bundle.getString("Url");

        mPresenter.Fetch_TuPian_Img(Url);

        srlAndroid.setOnRefreshListener(this);

        mAdapter = new TuPian_Home_Adapter(dataBean, getSupportActivity(),"TuPian");
        Book_Dir_List.setLayoutManager(new GridLayoutManager(getSupportActivity(), 2));
        Book_Dir_List.setAdapter(mAdapter);


        mAdapter.setOnItemClickListener(new TuPian_Home_Adapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(TuPianHomeBean item) {
                String itemUrl = item.getUrl();
                String subUrl= item.getUrl();
                itemUrl = StringUtlis.suString(itemUrl, "/");
                if (itemUrl.equals("xiuren")) {
                    Intent intent = new Intent(getActivity(), TuPianImgContentActivity.class);
                    intent.putExtra("ImgUrl", Url + item.getUrl());
                    intent.putExtra("Url", Url + itemUrl + "/");
                    intent.putExtra("Type", "TuPian");
                    startActivity(intent);
                }else {
                    subUrl = StringUtlis.subString(subUrl, "/");
                    subUrl = StringUtlis.subString(subUrl, "/");

                    Intent intent = new Intent(getActivity(), TuPianImgContentActivity.class);
                    intent.putExtra("ImgUrl", Url + subUrl);
                    intent.putExtra("Url", Url);
                    intent.putExtra("Type", "TuPian");

                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void attachView() {
        mPresenter.attachView(this);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public void showError(String message) {
        LogUtils.e(message);
    }

    @Override
    public void Fetch_TuPian_Img_Success(List<TuPianHomeBean> dataBean) {

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
    public void onRefresh() {
        isRefresh = true;
        mAdapter.setEnableLoadMore(false);
        mPresenter.Fetch_TuPian_Img(Url);
    }
}
