package com.wangzl.common;

import android.app.Application;
import android.util.Log;

/**
 * Created by wangzl on 2016/1/4.
 *
 * 用于Common module需要使用Context的情况
 * 在{@link Application#onCreate()}方法中初始化，将Application传入
 * Common module内通过该类获取Application作为Context使用
 */
public class AppContextWrapper {
	public static boolean DEBUG;
	private static AppContextWrapper mSingleton;
	private Application mAppContext;

	private AppContextWrapper() {}

	private AppContextWrapper(Application application) {
		mAppContext = application;
		if (mAppContext instanceof ILibBuildConfig) {
			DEBUG = ((ILibBuildConfig) mAppContext).isDebugBuild();
		}
	}

	public static void init(Application application) {
		mSingleton = new AppContextWrapper(application);
	}

	public static AppContextWrapper getSingleton() {
		return mSingleton;
	}

	public Application getApp() {
		return mAppContext;
	}
}
