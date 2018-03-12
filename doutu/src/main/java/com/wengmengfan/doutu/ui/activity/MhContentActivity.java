package com.wengmengfan.doutu.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wengmengfan.doutu.R;
import com.wengmengfan.doutu.base.BaseActivity;
import com.wengmengfan.doutu.bean.MhContentBean;
import com.wengmengfan.doutu.component.AppComponent;
import com.wengmengfan.doutu.component.DaggerMainComponent;
import com.wengmengfan.doutu.presenter.contract.MhContentContract;
import com.wengmengfan.doutu.presenter.impl.MhContentActivityPresenter;
import com.wengmengfan.doutu.ui.adapter.MhContentAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * sayid ....
 * Created by wengmf on 2018/2/23.
 */

public class MhContentActivity extends BaseActivity implements MhContentContract.View {

    @Inject
    MhContentActivityPresenter mPresenter;

    @BindView(R.id.img_rv)
    RecyclerView imgRv;

    private String ImgUrl, BookNum,Type,Url;
    private MhContentAdapter mhContentAdapter;

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
        Type = getIntent().getStringExtra("Type");
        switch (Type){
            case  "ManHuan":

                ImgUrl = getIntent().getStringExtra("ImgUrl");
                BookNum = getIntent().getStringExtra("BookNum");
                mPresenter.Fetch_ImgInfo(ImgUrl);

                break;
            case  "TuPian":

                ImgUrl = getIntent().getStringExtra("ImgUrl");
                Url = getIntent().getStringExtra("Url");
                mPresenter.Fetch_TuPian_ImgInfo_Success(ImgUrl,Url);
                break;
            case  "":
                break;

        }


        mhContentAdapter = new MhContentAdapter( null,this);
        imgRv.setLayoutManager(new GridLayoutManager(this, 1));
        imgRv.setAdapter(mhContentAdapter);

    }


    @Override
    public void Fetch_ImgInfo_Success(List<MhContentBean> data) {
        mhContentAdapter.setNewData(data);
    }

    @Override
    public void Fetch_TuPian_ImgInfo_Success(List<MhContentBean> data) {
        mhContentAdapter.setNewData(data);
    }

    @Override
    public void showError(String message) {

    }

}
