package com.wengmengfan.sttwang.base;

import android.app.Application;

import com.blankj.utilcode.utils.ThreadPoolUtils;
import com.blankj.utilcode.utils.Utils;
import com.bugtags.library.Bugtags;
import com.wengmengfan.sttwang.component.AppComponent;
import com.wengmengfan.sttwang.component.DaggerAppComponent;
import com.wengmengfan.sttwang.module.ApiModule;
import com.wengmengfan.sttwang.module.AppModule;
import com.wengmengfan.sttwang.utils.AppUtils;
import com.wengmengfan.sttwang.utils.UmengUtil;

import static com.bugtags.library.Bugtags.BTGInvocationEventNone;



public class BaseApplication extends Application {

    private static AppComponent appComponent;

    public  static BaseApplication baseApplication;

    public  static ThreadPoolUtils MAIN_EXECUTOR =   new ThreadPoolUtils(ThreadPoolUtils.Type.FixedThread,5);


    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        //将我们自己的MyApplication中的所有逻辑放在这里，例如初始化一些第三方

        initCompoent();
        Utils.init(this);
        AppUtils.init(this);
        UmengUtil.UmengUtilInit(this);
        Bugtags.start("beb9b4f14e72470fe0ad088b715ec421", this, BTGInvocationEventNone);
        UmengUtil.onEvent("phoneInfo");

    }




    private void initCompoent() {
        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .build();
    }



    /**
     * 获取BaseApplication实例
     * @return
     */

    public static BaseApplication getBaseApplication(){
        return baseApplication;
    }

    public static AppComponent getAppComponent(){
        return appComponent;
    }
}
