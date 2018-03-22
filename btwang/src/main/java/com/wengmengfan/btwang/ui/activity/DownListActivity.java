package com.wengmengfan.btwang.ui.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.blankj.utilcode.utils.FileUtils;
import com.wengmengfan.btwang.R;
import com.wengmengfan.btwang.base.BaseActivity;
import com.wengmengfan.btwang.base.BaseApplication;
import com.wengmengfan.btwang.bean.DownVideoBean;
import com.wengmengfan.btwang.component.AppComponent;
import com.wengmengfan.btwang.ui.adapter.DownListApadter;
import com.wengmengfan.btwang.ui.adapter.DownRanking_Adapter;
import com.wengmengfan.btwang.ui.fragment.DownRankingFragment;
import com.wengmengfan.btwang.utils.DeviceUtils;
import com.wengmengfan.btwang.utils.NotificationHandler;
import com.wengmengfan.btwang.view.MyLoadMoreView;
import com.xunlei.downloadlib.XLTaskHelper;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DownListActivity extends BaseActivity {

    @BindView(R.id.tv_down)
    TextView tvDown;
    @BindView(R.id.rv_down)
    RecyclerView rvDown;
    @BindView(R.id.tv_down_ok)
    TextView tvDownOk;
    @BindView(R.id.rv_down_ok)
    RecyclerView rvDownOk;
    private NotificationHandler nHandler;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_down_list;
    }

    @Override
    public void attachView() {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void initView() {

        nHandler  = NotificationHandler.getInstance(this);
        final DownListApadter mAdapter = new DownListApadter(BaseApplication.downVideoBeanList, DownListActivity.this);
        rvDown.setLayoutManager(new LinearLayoutManager(DownListActivity.this));
        rvDown.setAdapter(mAdapter);

        mAdapter.OnDeleteItemListenter(new DownListApadter.OnDeleteItemListenter() {
            @Override
            public void OnDeleteItemListenter(DownVideoBean item) {
               String   videoPath = DeviceUtils.getSDVideoPath(item.getPlayTitle());
                XLTaskHelper.deleteTask(item.getTaskId(), videoPath);
                BaseApplication.downVideoBeanList.remove(item);
                nHandler.cancelNotification((int) item.getTaskId());
                mAdapter.notifyDataSetChanged();
            }
        });

        String sdDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Video";
        List<File> files = FileUtils.listFilesInDir(sdDir);




    }
}
