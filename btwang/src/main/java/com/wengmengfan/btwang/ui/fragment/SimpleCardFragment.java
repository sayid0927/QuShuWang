package com.wengmengfan.btwang.ui.fragment;

import android.annotation.SuppressLint;

import com.wengmengfan.btwang.R;
import com.wengmengfan.btwang.base.BaseFragment;
import com.wengmengfan.btwang.component.AppComponent;

@SuppressLint("ValidFragment")
public class SimpleCardFragment extends BaseFragment {
    private String mTitle;

    public static SimpleCardFragment getInstance(String title) {
        SimpleCardFragment sf = new SimpleCardFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    public void loadData() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_simplecar;
    }

    @Override
    public void attachView() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }
}