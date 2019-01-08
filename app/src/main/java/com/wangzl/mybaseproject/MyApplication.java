package com.wangzl.mybaseproject;

import android.app.Application;

import com.wangzl.common.AppContextWrapper;
import com.wangzl.common.ILibBuildConfig;
import com.wangzl.common.network.NetworkManager;

/**
 * @Author wangzl
 * @Date 2019/1/8 15:41
 * @Description
 * 自定义Application
 * 注意：不要随意在此类中添加代码，特别是在onCreate方法中
 * 避免影响应用启动速度，建议只添加需要在应用启动时全局初始化的功能
 */
public class MyApplication extends Application implements ILibBuildConfig{
    private static MyApplication mApp;

    public static MyApplication getApp() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;

        AppContextWrapper.init(this);
        //初始化网络管理类
        NetworkManager.init();
    }

    @Override
    public boolean isDebugBuild() {
        return BuildConfig.DEBUG;
    }
}
