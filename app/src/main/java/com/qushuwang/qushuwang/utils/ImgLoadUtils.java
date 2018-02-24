package com.qushuwang.qushuwang.utils;

import android.content.Context;
import android.widget.ImageView;

import com.blankj.utilcode.utils.Utils;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

/**
 * sayid ....
 * Created by wengmf on 2018/2/24.
 */

public class ImgLoadUtils {

    public static void loadImage(Context context ,String url, ImageView iv) {
        Picasso.with(context).load(url).into(iv);
    }

}
