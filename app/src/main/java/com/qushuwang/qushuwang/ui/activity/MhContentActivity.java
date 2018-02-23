package com.qushuwang.qushuwang.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.orhanobut.logger.Logger;
import com.qushuwang.qushuwang.R;
import com.qushuwang.qushuwang.base.BaseActivity;
import com.qushuwang.qushuwang.bean.MhContentBean;
import com.qushuwang.qushuwang.component.AppComponent;
import com.qushuwang.qushuwang.component.DaggerMainComponent;
import com.qushuwang.qushuwang.presenter.contract.MhContentContract;
import com.qushuwang.qushuwang.presenter.impl.MhContentActivityPresenter;
import com.qushuwang.qushuwang.ui.adapter.ChapterAdapter;
import com.qushuwang.qushuwang.ui.adapter.MhContentAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * sayid ....
 * Created by wengmf on 2018/2/23.
 */

public class MhContentActivity extends BaseActivity implements MhContentContract.View {

    @Inject
    MhContentActivityPresenter mPresenter;

    @BindView(R.id.img_rv)
    RecyclerView imgRv;

    private String ImgUrl, BookNum;
    private  MhContentAdapter mhContentAdapter;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mhcontent;
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
        ImgUrl = getIntent().getStringExtra("ImgUrl");
        BookNum = getIntent().getStringExtra("BookNum");
        mPresenter.Fetch_ImgInfo(ImgUrl);


        mhContentAdapter = new MhContentAdapter( null,this);
        imgRv.setLayoutManager(new GridLayoutManager(this, 1));
        imgRv.setAdapter(mhContentAdapter);

    }


    @Override
    public void Fetch_ImgInfo_Success(List<MhContentBean> data) {
        mhContentAdapter.setNewData(data);
    }

    @Override
    public void showError(String message) {

    }

}
