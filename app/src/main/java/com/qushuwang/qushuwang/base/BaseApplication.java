package com.qushuwang.qushuwang.base;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;

import com.bugtags.library.Bugtags;
import com.qushuwang.qushuwang.component.AppComponent;
import com.qushuwang.qushuwang.component.DaggerAppComponent;
import com.qushuwang.qushuwang.module.ApiModule;
import com.qushuwang.qushuwang.module.AppModule;
import com.qushuwang.qushuwang.tinker.MyLogImp;
import com.qushuwang.qushuwang.tinker.TinkerManager;
import com.qushuwang.qushuwang.utils.AppUtils;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import static com.bugtags.library.Bugtags.BTGInvocationEventNone;

@SuppressWarnings("unused")
@DefaultLifeCycle(  application = "com.qushuwang.qushuwang.MyApplication",// 自定义生成
                    flags = ShareConstants.TINKER_ENABLE_ALL,
                    loadVerifyFlag = false)


public class BaseApplication extends ApplicationLike {



    public  static BaseApplication baseApplication;

    private static AppComponent appComponent;

    public BaseApplication(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag,
                             long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        //将我们自己的MyApplication中的所有逻辑放在这里，例如初始化一些第三方

        initCompoent();
        AppUtils.init(this.getApplication());
        Bugtags.start("beb9b4f14e72470fe0ad088b715ec421", this.getApplication(), BTGInvocationEventNone);

    }




    private void initCompoent() {
        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this.getApplication()))
                .build();
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);

        // 其原理是分包架构，所以在加载初要加载其余的分包
        MultiDex.install(base);

        // Tinker管理类，保存当前对象
        TinkerManager.setTinkerApplicationLike(this);
        // 崩溃保护
        TinkerManager.initFastCrashProtect();
        // 是否重试
        TinkerManager.setUpgradeRetryEnable(true);

        //Log 实现，打印加载补丁的信息
        TinkerInstaller.setLogIml(new MyLogImp());

        // 运行Tinker ，通过Tinker添加一些基本配置
        TinkerManager.installTinker(this);
        Tinker tinker = Tinker.with(getApplication());

    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {
        // 生命周期，默认配置
        getApplication().registerActivityLifecycleCallbacks(callback);
    }

    /**
     * 获取SampleApplication实例
     * @return
     */

    public static BaseApplication getBaseApplication(){
        return baseApplication;
    }

    public static AppComponent getAppComponent(){
        return appComponent;
    }


}
