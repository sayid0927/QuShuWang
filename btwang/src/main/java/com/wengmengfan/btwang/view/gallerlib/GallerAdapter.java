package com.wengmengfan.btwang.view.gallerlib;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

/**
 * Created by hucanhui on 2017/3/31.
 */
public abstract class GallerAdapter extends PagerAdapter {

    private View view;

    public abstract int getGallerSize();

    public abstract View getItemView(int position);

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (position != 0 && getGallerSize() != 0) {
            position %= getGallerSize();
            if (position <= 0) {
                position = getGallerSize() + position;
            }
            view = getItemView(position);
            container.addView(view);
            return view;
        } else
            return view;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((View) object);
    }


}
