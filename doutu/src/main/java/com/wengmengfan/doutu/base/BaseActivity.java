package com.wengmengfan.doutu.base;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.bugtags.library.Bugtags;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.umeng.analytics.MobclickAgent;
import com.wengmengfan.doutu.component.AppComponent;
import com.wengmengfan.doutu.tinker.Utils;

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
        TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip");
        synchronized (mActivities) {
            mActivities.add(this);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Bugtags.onResume(this);
        MobclickAgent.onResume(this);
        Utils.setBackground(false);
    }

    public void onPause() {
        super.onPause();
        Bugtags.onPause(this);
        MobclickAgent.onPause(this);
        Utils.setBackground(true);
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
