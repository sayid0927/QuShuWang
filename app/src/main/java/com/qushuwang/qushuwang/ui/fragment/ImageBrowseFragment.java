package com.qushuwang.qushuwang.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.orhanobut.logger.Logger;
import com.qushuwang.qushuwang.R;
import com.qushuwang.qushuwang.base.BaseFragment;
import com.qushuwang.qushuwang.base.Constant;
import com.qushuwang.qushuwang.component.AppComponent;
import com.qushuwang.qushuwang.component.DaggerMainComponent;
import com.qushuwang.qushuwang.presenter.contract.ImgBrowseContract;
import com.qushuwang.qushuwang.presenter.impl.ImgBrowsePresenter;
import com.qushuwang.qushuwang.ui.activity.ImgContentActivity;
import com.qushuwang.qushuwang.ui.activity.TuPianImgContentActivity;
import com.qushuwang.qushuwang.utils.ImgLoadUtils;
import com.syd.oden.circleprogressdialog.core.CircleProgressDialog;

import java.io.InputStream;
import java.net.URL;

import javax.inject.Inject;

import butterknife.BindView;
import retrofit2.http.Url;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Administrator on 2017/11/4 0004.
 */

public class ImageBrowseFragment extends BaseFragment implements ImgBrowseContract.View {

    @Inject
    ImgBrowsePresenter mPresenter;

    @BindView(R.id.mPhotoView)
    ImageView mPhotoView;

    private String imgUrl;
    PhotoViewAttacher mAttacher;

    private CircleProgressDialog circleProgressDialog;

    public static ImageBrowseFragment newInstance(String imgUrl) {
        ImageBrowseFragment manHuan_name = new ImageBrowseFragment();
        Bundle bundle = new Bundle();
        bundle.putString("imgUrl", imgUrl);
        manHuan_name.setArguments(bundle);
        return manHuan_name;
    }

    @Override
    protected void initView(Bundle bundle) {
        imgUrl = bundle.getString("imgUrl");
        circleProgressDialog = new CircleProgressDialog(getActivity());
        mPresenter.Fetch_TuPian_Img(imgUrl);
        Logger.e("imgUrl >> "+imgUrl);
    }

    @Override
    public void loadData() {
        setState(Constant.STATE_SUCCESS);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_imagebrowse;
    }

    @Override
    public void attachView() {
        mPresenter.attachView(this);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public void downloadPicFromNet_Success(String filePath) {
        Toast.makeText(getSupportActivity(), "已保存图片在  " + filePath, Toast.LENGTH_LONG).show();
    }

    @Override
    public void Fetch_TuPian_Img_Success(String Url) {
        circleProgressDialog.showDialog();

        Glide.with(getActivity())
                .load(Url)
                .asBitmap()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .error(R.mipmap.img_error)
                .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                        if (circleProgressDialog.isShowing())
                            circleProgressDialog.dismiss();
                        mPhotoView.setImageBitmap(resource);
                        mAttacher = new PhotoViewAttacher(mPhotoView);
                        mAttacher.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                showNoticeDialog();
                                return false;
                            }
                        });

                        mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                            @Override
                            public void onPhotoTap(View view, float x, float y) {
                                if (TuPianImgContentActivity.install != null && TuPianImgContentActivity.install.getConnectionTitle() == View.INVISIBLE)
                                    TuPianImgContentActivity.install.setConnectionTitle(View.VISIBLE);
                                else
                                    TuPianImgContentActivity.install.setConnectionTitle(View.INVISIBLE);
                            }

                            @Override
                            public void onOutsidePhotoTap() {

                            }
                        });
                    }
                });
    }

    @Override
    public void showError(String message) {

    }

    private void showNoticeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getSupportActivity());
        builder.setTitle("下载图片 ");
        builder.setPositiveButton("下载图片", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mPresenter.downloadPicFromNet(imgUrl);

            }
        });
        Dialog noticeDialog = builder.create();
        noticeDialog.show();
    }
}
