package com.wengmengfan.sttwang.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.bugtags.library.Bugtags;
import com.umeng.analytics.MobclickAgent;
import com.wengmengfan.sttwang.component.AppComponent;


import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseActivity  extends AppCompatActivity    {


    // 管理运行的所有的activity
    public final static List<AppCompatActivity> mActivities = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        setupActivityComponent(BaseApplication.getBaseApplication().getAppComponent());
        attachView();
        initView();
        //加载补丁包
        synchronized (mActivities) {
            mActivities.add(this);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Bugtags.onResume(this);
        MobclickAgent.onResume(this);

    }

    public void onPause() {
        super.onPause();
        Bugtags.onPause(this);
        MobclickAgent.onPause(this);

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Bugtags.onDispatchTouchEvent(this, event);
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        synchronized (mActivities) {
            mActivities.remove(this);
        }
        detachView();
    }

    public    void killAll() {
        // 复制了一份mActivities 集合Å
        List<AppCompatActivity> copy;
        synchronized (mActivities) {
            copy = new LinkedList<>(mActivities);
        }
        for (AppCompatActivity activity : copy) {
            activity.finish();
        }
        // 杀死当前的进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    protected abstract void setupActivityComponent(AppComponent appComponent);
    public abstract int getLayoutId();
    public abstract void attachView();
    public abstract void detachView();
    public abstract void initView();

}
