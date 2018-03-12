package com.wengmengfan.btwang.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wengmengfan.btwang.R;
import com.wengmengfan.btwang.base.BaseActivity;
import com.wengmengfan.btwang.bean.ViewBoxBean;
import com.wengmengfan.btwang.component.AppComponent;
import com.wengmengfan.btwang.component.DaggerMainComponent;
import com.wengmengfan.btwang.presenter.contract.ViewBoxContract;
import com.wengmengfan.btwang.presenter.impl.ViewBoxPresenter;
import com.wengmengfan.btwang.utils.ImgLoadUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * sayid ....
 * Created by wengmf on 2018/3/12.
 */

public class ViewBoxActivity extends BaseActivity implements ViewBoxContract.View {

    @Inject
    ViewBoxPresenter mPresenter;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.llExit)
    LinearLayout llExit;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.size)
    TextView size;
    @BindView(R.id.sizeNum)
    TextView sizeNum;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_viewbox;
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
        String Url = "http://www.zei8.me" + getIntent().getStringExtra("Url");
        mPresenter.Fetch_ViewBoxInfo(Url);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void Fetch_ViewBoxInfo_Success(ViewBoxBean data) {
        ImgLoadUtils.loadImage(ViewBoxActivity.this, data.getImgUrl(), img);
        title.setText(data.getAlt());
        tvTitle.setText(data.getAlt());
        size.setText(data.getSize());
        sizeNum.setText(data.getSizeNum());
    }


    @OnClick({R.id.llExit, R.id.tvTitle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llExit:
                this.finish();
                break;
            case R.id.tvTitle:
                break;
        }
    }
}
