package com.wengmengfan.doutu.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.FileUtils;
import com.wengmengfan.doutu.R;
import com.wengmengfan.doutu.base.BaseActivity;
import com.wengmengfan.doutu.component.AppComponent;
import com.wengmengfan.doutu.ui.adapter.DownListPicAdapter;
import com.wengmengfan.doutu.utils.DeviceUtils;
import com.wengmengfan.doutu.view.MyLoadMoreView;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * sayid ....
 * Created by wengmf on 2018/3/12.
 */

public class DownListPicActivity extends BaseActivity {

    @BindView(R.id.llExit)
    LinearLayout llExit;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    private List<File> fileList;
    private List<String> stringList;
    private DownListPicAdapter downListPicAdapter;

    @BindView(R.id.rv_list)
    RecyclerView rvList;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_downlistpic;
    }

    @Override
    public void attachView() {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void initView() {

        tvTitle.setText("下载过的图片");

        String destFileDir = DeviceUtils.getSDPath();
        boolean isFile = FileUtils.createOrExistsDir(destFileDir);
        if (isFile) {
            fileList = FileUtils.listFilesInDir(destFileDir);
        }
        downListPicAdapter = new DownListPicAdapter(fileList, this);
        downListPicAdapter.setLoadMoreView(new MyLoadMoreView());
        rvList.setLayoutManager(new GridLayoutManager(this, 2));
        rvList.setAdapter(downListPicAdapter);
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
