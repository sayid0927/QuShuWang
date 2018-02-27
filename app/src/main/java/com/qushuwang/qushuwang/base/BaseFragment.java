package com.qushuwang.qushuwang.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qushuwang.qushuwang.component.AppComponent;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/11/4 0004.
 */

public abstract class BaseFragment extends Fragment {


    protected View parentView;
    protected FragmentActivity activity;
    protected LayoutInflater inflater;

    protected Context mContext;

    private boolean mIsVisible = false;     // fragment是否显示了


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        parentView = inflater.inflate(getLayoutResId(), container, false);
        activity = getSupportActivity();
        mContext = activity;
        this.inflater = inflater;
        ButterKnife.bind(this, parentView);
        return parentView;
    }

//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        ButterKnife.bind(this, view);
//        setupActivityComponent(BaseApplication.getBaseApplication().getAppComponent());
//        attachView();
//        if (getArguments() != null) {
//            initView(getArguments());
//        }else {
//            initView();
//        }
//        if(mIsVisible){
//            loadData();
//        }
//
//    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            setupActivityComponent(BaseApplication.getBaseApplication().getAppComponent());
            attachView();
            if (getArguments() != null) {
                initView(getArguments());
            }else {
                initView();
            }
            mIsVisible=isVisibleToUser;
           loadData();
        }
    }

    public abstract void loadData();
    public abstract int getLayoutResId();

    public abstract void attachView();

    protected void initView(Bundle bundle) {
    }

    ;

    protected void initView() {
    }

    ;

    protected abstract void setupActivityComponent(AppComponent appComponent);

    public FragmentActivity getSupportActivity() {
        return super.getActivity();
    }
}
