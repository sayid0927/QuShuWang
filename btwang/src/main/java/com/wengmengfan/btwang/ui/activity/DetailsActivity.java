package com.wengmengfan.btwang.ui.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.wengmengfan.btwang.R;
import com.wengmengfan.btwang.base.BaseActivity;
import com.wengmengfan.btwang.bean.VideoDetailsBean;
import com.wengmengfan.btwang.component.AppComponent;
import com.wengmengfan.btwang.component.DaggerMainComponent;
import com.wengmengfan.btwang.presenter.contract.DetailsActivityContract;
import com.wengmengfan.btwang.presenter.impl.DetailsActivityPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

public class DetailsActivity extends BaseActivity implements DetailsActivityContract.View {

    @Inject
    DetailsActivityPresenter mPresenter;
    @BindView(R.id.llExit)
    LinearLayout llExit;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.size)
    TextView size;
    @BindView(R.id.sizeNum)
    TextView sizeNum;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.down_Video)
    Button downVideo;
    @BindView(R.id.play_Video)
    Button playVideo;
    private String HrefUrl, imgUrl, Title;

    private int clickType;


    private String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    private ProgressDialog loadPd, commonPd;


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_details;
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

        imgUrl = getIntent().getStringExtra("imgUrl");
        HrefUrl = "https://www.80s.tt" + getIntent().getStringExtra("HrefUrl");
        Title = getIntent().getStringExtra("Title");
        mPresenter.Fetch_VideoDetailsInfo(HrefUrl);

    }

    @Override
    public void showError(String message) {

    }

    @OnClick({R.id.llExit, R.id.down_Video, R.id.play_Video})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llExit:
                this.finish();
                break;

            case R.id.down_Video:
                clickType = 0;
                if (!EasyPermissions.hasPermissions(this, perms)) {
                    EasyPermissions.requestPermissions(this, "需要读写权限", 1000, perms);
                } else {
//                    mPresenter.Fetch_HrefUrl(hrefUrl);
                }
                break;
            case R.id.play_Video:
                loadPd.show();
                clickType = 1;
                if (!EasyPermissions.hasPermissions(this, perms)) {
                    EasyPermissions.requestPermissions(this, "需要读写权限", 1000, perms);
                } else {
//                    mPresenter.Fetch_HrefUrl(hrefUrl);
                }
                break;
        }
    }

    @Override
    public void Fetch_VideoDetailsInfo_Success(VideoDetailsBean data) {
        Logger.e(data.toString());
    }

}
