package com.qushuwang.qushuwang.utils;

import android.content.Context;
import android.widget.ImageView;

import com.blankj.utilcode.utils.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qushuwang.qushuwang.R;

/**
 * Created by quantan.liu on 2017/3/23.
 */

public class GlideUtils {
    /**
     * 首页zhihu item读取图片
     * @param imgNumber 图片大小1最大 2中等 3最小 正方形的
     * @param url
     * @param image
     */
    public static void loadImage(int imgNumber, String url, ImageView image) {
        Glide.with(Utils.getContext())
                .load(url)
                .crossFade(1500)
                .error(getDefaultPic(imgNumber))
                .into(image);
    }

    private static int getDefaultPic(int imgNumber) {
        switch (imgNumber) {
            case 1:
                return R.mipmap.mm;
            case 2:
                return R.mipmap.mm;
            case 3:
                return R.mipmap.mm;
            case 4:
                return R.mipmap.mm;
        }
        return R.mipmap.mm;
    }

    public static void load(Context mContext, String url, ImageView iv) {    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
        Glide.with(mContext).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
    }

    public static void loadDetailImg(Context mContext, String url, ImageView iv) {    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
        Glide.with(mContext).load(url).asBitmap()
                .placeholder(R.mipmap.mm)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .error(R.mipmap.mm)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv);
    }

    public static void loadMovieTopImg(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .crossFade(500)
                .error(getDefaultPic(4))
                .into(imageView);
    }

    public static void loadMovieLatestImg(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .crossFade(500)
//                .override((int) CommonUtils.getDimens(R.dimen.movie_detail_width), (int) CommonUtils.getDimens(R.dimen.movie_detail_height))
                .placeholder(getDefaultPic(4))
                .error(getDefaultPic(4))
                .into(imageView);
    }
}
