package com.wengmengfan.btwang.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wengmengfan.btwang.R;
import com.wengmengfan.btwang.base.BaseActivity;
import com.wengmengfan.btwang.component.AppComponent;
import com.wengmengfan.btwang.component.DaggerMainComponent;
import com.wengmengfan.btwang.presenter.contract.MoreActivityContract;
import com.wengmengfan.btwang.presenter.impl.DetailsActivityPresenter;
import com.wengmengfan.btwang.presenter.impl.MoreActivityPresenter;

import javax.inject.Inject;

public class MoreActivity extends BaseActivity implements MoreActivityContract.View {

    @Inject
    MoreActivityPresenter mPresenter;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_more;
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

    }

    @Override
    public void showError(String message) {

    }
}
