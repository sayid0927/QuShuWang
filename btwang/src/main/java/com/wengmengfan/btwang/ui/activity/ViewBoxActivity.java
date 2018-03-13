package com.wengmengfan.btwang.ui.activity;

import android.Manifest;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.FileUtils;
import com.blankj.utilcode.utils.RegexUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.blankj.utilcode.utils.ZipUtils;
import com.orhanobut.logger.Logger;
import com.wengmengfan.btwang.R;
import com.wengmengfan.btwang.base.BaseActivity;
import com.wengmengfan.btwang.bean.DownHrefBean;
import com.wengmengfan.btwang.bean.ViewBoxBean;
import com.wengmengfan.btwang.component.AppComponent;

import com.wengmengfan.btwang.component.DaggerMainComponent;
import com.wengmengfan.btwang.presenter.contract.ViewBoxContract;
import com.wengmengfan.btwang.presenter.impl.ViewBoxPresenter;
import com.wengmengfan.btwang.utils.DeviceUtils;
import com.wengmengfan.btwang.utils.ImgLoadUtils;
import com.xunlei.downloadlib.XLTaskHelper;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * sayid ....
 * Created by wengmf on 2018/3/12.
 */

public class ViewBoxActivity extends BaseActivity implements ViewBoxContract.View ,EasyPermissions.PermissionCallbacks {

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
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.down_Video)
    Button downVideo;
    @BindView(R.id.play_Video)
    TextView playVideo;

    private String hrefUrl;
    private  DownHrefBean downHrefBean;

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

//        XLTaskHelper.init(getApplicationContext());

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
        content.setText(data.getContext());
        hrefUrl = "http://www.zei8.me" + data.getHref();

    }

    @Override
    public void Fetch_HrefUrl_Success(DownHrefBean data) {
        this.downHrefBean =data;
        mPresenter.download_Zip(data);
    }

    @Override
    public void download_Zip_Success(String filePath) {
        String  destFileDir = DeviceUtils.getSDPath(downHrefBean.getTitle());
        String  videoPath = DeviceUtils.getSDVideoPath(downHrefBean.getTitle());
        
        try {
            boolean jieya = ZipUtils.unzipFile(filePath, destFileDir);
            if(jieya) {
                FileUtils.deleteFile(filePath);
                List<File> files = FileUtils.listFilesInDir(destFileDir);
                String torrFile = null;
                for (File f : files) {
                    if (f.getAbsolutePath().endsWith(".torrent")) {
                        torrFile = f.getAbsolutePath();
                        try {
                            long dd = XLTaskHelper.instance(this).addTorrentTask(torrFile, videoPath, null);
                             Logger.e("DD >>  "+dd);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        ToastUtils.showLongToast(torrFile);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.llExit, R.id.tvTitle, R.id.down_Video, R.id.play_Video})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llExit:
                this.finish();

                break;
            case R.id.down_Video:

                break;
            case R.id.play_Video:

                String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE};

                if (!EasyPermissions.hasPermissions(this, perms)) {
                    EasyPermissions.requestPermissions(this, "需要读写权限",
                            1000, perms);
                } else {
                    mPresenter.Fetch_HrefUrl(hrefUrl);
                }
                break;
            case R.id.tvTitle:

                break;

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        mPresenter.Fetch_HrefUrl(hrefUrl);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        ToastUtils.showLongToast("没有权限无法下载电影");
    }

}
