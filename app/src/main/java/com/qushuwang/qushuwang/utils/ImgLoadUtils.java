package com.qushuwang.qushuwang.utils;

import android.content.Context;
import android.widget.ImageView;

import com.blankj.utilcode.utils.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qushuwang.qushuwang.R;
import com.qushuwang.qushuwang.ui.activity.MainActivity;
import com.squareup.picasso.Picasso;

/**
 * sayid ....
 * Created by wengmf on 2018/2/24.
 */

public class ImgLoadUtils {

    public static void loadImage(Context context ,String url, ImageView iv) {
        Picasso.with(context)
                .load(url)
                .error(R.mipmap.img_error)
                .into(iv);
    }

    public static void GifloadImage(Context context ,String url, ImageView iv) {
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .error(R.mipmap.img_error).
                into(iv);
    }

}
