package com.qushuwang.qushuwang.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.qushuwang.qushuwang.R;
import com.qushuwang.qushuwang.base.BaseFragment;
import com.qushuwang.qushuwang.component.AppComponent;
import com.qushuwang.qushuwang.component.DaggerMainComponent;
import com.qushuwang.qushuwang.presenter.contract.ImgBrowseContract;
import com.qushuwang.qushuwang.presenter.impl.ImgBrowsePresenter;
import com.qushuwang.qushuwang.ui.activity.ImgContentActivity;
import com.syd.oden.circleprogressdialog.core.CircleProgressDialog;

import java.io.InputStream;
import java.net.URL;

import javax.inject.Inject;

import butterknife.BindView;
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
        new DownloadImageTask(mPhotoView, imgUrl).execute(imgUrl);

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
    public void showError(String message) {

    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        private String imgUrl;
        private CircleProgressDialog circleProgressDialog;

        public DownloadImageTask(ImageView bmImage, String imgUrl) {
            this.bmImage = bmImage;
            this.imgUrl = imgUrl;
            circleProgressDialog = new CircleProgressDialog(getSupportActivity());
        }

        @Override
        protected void onPreExecute() {
            circleProgressDialog.showDialog();
        }

        protected Bitmap doInBackground(String... urls) {

            String path = urls[0];
            Bitmap result = null;
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                //先设置为true，获取bitmap宽度、高度
                options.inJustDecodeBounds = true;
                InputStream in = new URL(path).openStream();
                result = BitmapFactory.decodeStream(in, null, options);
                in.close();
//                resetOptions(options);
                //后设置为false，加载进内存显示
                options.inJustDecodeBounds = false;
                // InputStream在读取完之后就到结尾了，需要再次打开才能重新读取，否则下面的result将返回null
                in = new URL(path).openStream();
                result = BitmapFactory.decodeStream(in, null, options);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        protected void onPostExecute(Bitmap result) {
            circleProgressDialog.dismiss();
            if (result != null) {
                bmImage.setImageBitmap(result);
                // PhotoViewAttacher绑定ImageView
                mAttacher = new PhotoViewAttacher(bmImage);
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
                        if (ImgContentActivity.install.getConnectionTitle() == View.INVISIBLE)
                            ImgContentActivity.install.setConnectionTitle(View.VISIBLE);
                        else
                            ImgContentActivity.install.setConnectionTitle(View.INVISIBLE);
                    }
                });
            }
        }
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
