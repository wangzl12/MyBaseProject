package com.wangzl.mybaseproject.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.wangzl.common.BuildConfig;
import com.wangzl.common.network.toolbox.BigTreeCall;
import com.wangzl.common.network.toolbox.NetworkCallContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangzl
 * @Date 2019/1/8 17:43
 * @Description Activity基类，所有Activity继承此类
 */
public class BaseActivity extends FragmentActivity implements NetworkCallContext{

    protected final String TAG = getClass().getName();
    protected BaseActivity mContext;
    protected List<BigTreeCall> mCallList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }



    @Override
    protected void onResume() {
        super.onResume();
        if (BuildConfig.DEBUG) {
            Log.i("onResume", "Activity onResume class = " + TAG);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        cancelAll();
        super.onDestroy();
    }

    @Override
    public void addNetworkCall(BigTreeCall call) {
        mCallList.add(call);
    }

    @Override
    public void cancelAll() {
        for (BigTreeCall call : mCallList) {
            if (call == null || call.isCanceled()) {
                continue;
            }
            call.cancel();
        }
    }

}
