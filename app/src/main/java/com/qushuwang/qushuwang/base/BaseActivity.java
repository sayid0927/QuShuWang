package com.qushuwang.qushuwang.base;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.bugtags.library.Bugtags;
import com.qushuwang.qushuwang.component.AppComponent;
import com.qushuwang.qushuwang.tinker.Utils;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;

public abstract class BaseActivity  extends AppCompatActivity    {


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
        detachView();
    }

    protected abstract void setupActivityComponent(AppComponent appComponent);

    public abstract int getLayoutId();
    public abstract void attachView();
    public abstract void detachView();
    public abstract void initView();
}
