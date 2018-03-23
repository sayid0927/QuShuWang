package com.wengmengfan.btwang.utils;

import android.content.Context;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.wengmengfan.btwang.R;
import com.wengmengfan.btwang.base.BaseApplication;

import java.util.concurrent.ExecutionException;

/**
 * sayid ....
 * Created by wengmf on 2018/2/24.
 */

public class ImgLoadUtils {

    public static void loadImage(Context context ,String url, ImageView iv) {
//        Picasso.with(context)
//                .load(url)
//                .error(R.mipmap.img_error)
//                .into(iv);

        Glide.with(context).load(url)
                .centerCrop()
                .override(400, 250)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .error(R.mipmap.img_error).
                into(iv);
    }

    public static void GifloadImage(Context context ,String url, ImageView iv) {
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .error(R.mipmap.img_error).
                into(iv);
    }

}
