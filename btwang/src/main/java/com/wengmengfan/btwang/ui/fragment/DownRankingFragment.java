package com.wengmengfan.btwang.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wengmengfan.btwang.R;
import com.wengmengfan.btwang.base.BaseFragment;
import com.wengmengfan.btwang.base.Constant;
import com.wengmengfan.btwang.bean.DownRaningBean;
import com.wengmengfan.btwang.component.AppComponent;
import com.wengmengfan.btwang.component.DaggerMainComponent;
import com.wengmengfan.btwang.presenter.contract.DownRankingContract;
import com.wengmengfan.btwang.presenter.impl.DownRankingPresenter;
import com.wengmengfan.btwang.ui.activity.ViewBoxActivity;
import com.wengmengfan.btwang.ui.adapter.DownRanking_Adapter;
import com.wengmengfan.btwang.view.MyLoadMoreView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * sayid ....
 * Created by wengmf on 2018/3/12.
 */

public class DownRankingFragment extends BaseFragment implements DownRankingContract.View, SwipeRefreshLayout.OnRefreshListener {


    @Inject
    DownRankingPresenter mPresenter;

    @BindView(R.id.title_list)
    RecyclerView rvList;
    @BindView(R.id.srl_android)
    SwipeRefreshLayout srlAndroid;

    private DownRanking_Adapter mAdapter;
    private List<DownRaningBean> dataBean;
    private static String DownRankingUrl = "http://www.zei8.me/movie/top100/";

    private boolean isRefresh = false;

    @Override
    public void loadData() {
        setState(Constant.STATE_SUCCESS);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_downranking;
    }

    @Override
    protected void initView(Bundle bundle) {

        mPresenter.Fetch_DownRankingInfo(DownRankingUrl);
        mAdapter = new DownRanking_Adapter(dataBean, getSupportActivity());
        srlAndroid.setOnRefreshListener(this);
        rvList.setLayoutManager(new GridLayoutManager(getSupportActivity(), 1));
        rvList.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new DownRanking_Adapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(DownRaningBean item) {
                Intent intent = new Intent(getActivity(), ViewBoxActivity.class);
                intent.putExtra("Url",item.getHref());
                getActivity().startActivity(intent);

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
    public void onRefresh() {

        isRefresh = true;
        mAdapter.setEnableLoadMore(false);
        mPresenter.Fetch_DownRankingInfo(DownRankingUrl);

    }


    @Override
    public void showError(String message) {

    }

    @Override
    public void Fetch_DownRankingInfo_Success(List<DownRaningBean> dataBean) {
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
}
