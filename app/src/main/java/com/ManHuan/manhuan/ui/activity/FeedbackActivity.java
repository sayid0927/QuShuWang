package com.ManHuan.manhuan.ui.activity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.ManHuan.manhuan.R;
import com.ManHuan.manhuan.base.BaseActivity;
import com.ManHuan.manhuan.component.AppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * sayid ....
 * Created by wengmf on 2018/3/8.
 */

public class FeedbackActivity extends BaseActivity {


    @BindView(R.id.llExit)
    LinearLayout llExit;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    public void attachView() {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void initView() {

    }


    @OnClick(R.id.llExit)
    public void onViewClicked() {
        this.finish();
    }
}
