package com.wengmengfan.doutu.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wengmengfan.doutu.R;
import com.wengmengfan.doutu.base.BaseActivity;
import com.wengmengfan.doutu.component.AppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * sayid ....
 * Created by wengmf on 2018/3/8.
 */

public class AboutActivity extends BaseActivity {

    @BindView(R.id.llExit)
    LinearLayout llExit;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int getLayoutId() {

        return R.layout.activity_about_us;
    }

    @Override
    public void attachView() {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void initView() {
        tvTitle.setText("关于漫画");
    }

    @OnClick({R.id.llExit})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.llExit:
                this.finish();
                break;
        }
    }

}
