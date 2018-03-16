package com.wengmengfan.btwang.ui.activity;

import android.widget.LinearLayout;

import com.wengmengfan.btwang.R;
import com.wengmengfan.btwang.base.BaseActivity;
import com.wengmengfan.btwang.component.AppComponent;

import butterknife.BindView;
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
