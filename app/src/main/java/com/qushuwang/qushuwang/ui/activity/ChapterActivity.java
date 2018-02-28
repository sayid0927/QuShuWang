package com.qushuwang.qushuwang.ui.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qushuwang.qushuwang.R;
import com.qushuwang.qushuwang.base.BaseActivity;
import com.qushuwang.qushuwang.bean.BookInfoBean;
import com.qushuwang.qushuwang.component.AppComponent;
import com.qushuwang.qushuwang.component.DaggerMainComponent;
import com.qushuwang.qushuwang.presenter.contract.ChapterContract;
import com.qushuwang.qushuwang.presenter.impl.ChapterActivityPresenter;
import com.qushuwang.qushuwang.ui.adapter.ChapterAdapter;
import com.qushuwang.qushuwang.utils.ImgLoadUtils;
import com.qushuwang.qushuwang.utils.StringUtlis;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * sayid ....
 * Created by wengmf on 2018/2/11.
 */

public class ChapterActivity extends BaseActivity implements ChapterContract.View {
    @Inject
    ChapterActivityPresenter mPresenter;

    @BindView(R.id.llExit)
    LinearLayout llExit;

    @BindView(R.id.iv_bookImgUrl)
    ImageView ivBookImgUrl;
    @BindView(R.id.tv_bookName)
    TextView tvBookName;
    @BindView(R.id.tv_zuozhe)
    TextView tvZuozhe;
    @BindView(R.id.tv_biaoqian)
    TextView tvBiaoqian;
    @BindView(R.id.tv_update)
    TextView tvUpdate;
    @BindView(R.id.rl_chapter)
    RecyclerView rlChapter;

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    private ChapterAdapter chapterAdapter;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_chapter;
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
        mPresenter.Fetch_BookInfo(getIntent().getStringExtra("Url"));
        chapterAdapter = new ChapterAdapter(null, this);
        rlChapter.setLayoutManager(new GridLayoutManager(this, 2));
        rlChapter.setAdapter(chapterAdapter);


        chapterAdapter.setOnItemClickListener(new ChapterAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(BookInfoBean.BookChapterBean item) {
                Intent intent = new Intent(ChapterActivity.this, MhContentActivity.class);
                intent.putExtra("ImgUrl", item.getUrl());
                intent.putExtra("Type","ManHuan");
                intent.putExtra("BookNum", item.getNum());
                startActivity(intent);
            }
        });
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void Fetch_BookInfo_Success(BookInfoBean data) {

        tvBookName.setText(data.getBookName());
        tvTitle.setText(data.getBookName());
        tvZuozhe.setText(StringUtlis.subString(data.getZuoZhe(), "：").trim());
        tvBiaoqian.setText(StringUtlis.subString(data.getBiaoQian(), "：").trim());
        tvUpdate.setText(StringUtlis.subString(data.getRenqi(), "：").trim());

        ImgLoadUtils.loadImage(this,data.getImgUrl(),ivBookImgUrl);
        chapterAdapter.setNewData(data.getBookChapterBeanList());

    }


    @OnClick({R.id.llExit, R.id.tvTitle, R.id.llRight, R.id.connection_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llExit:
                this.finish();
                break;
            case R.id.tvTitle:
                break;
            case R.id.llRight:
                break;
            case R.id.connection_title:
                break;
        }
    }
}
