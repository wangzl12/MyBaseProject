package com.wangzl.common.network.interceptor;

import android.os.Looper;
import android.widget.Toast;

import com.wangzl.common.AppContextWrapper;
import com.wangzl.common.utils.NetUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author wangzl
 * @Date 2019/1/9 13:48
 * @Description 判断是否有网络的拦截器
 */
public class NoNetInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetUtils.isNetworkAvailable(AppContextWrapper.getSingleton().getApp())) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Looper.prepare();
                    Toast.makeText(AppContextWrapper.getSingleton().getApp(), "请检查网络", Toast.LENGTH_LONG).show();
                    //ToastUtil.showMsg("请检查您的网络");
                    Looper.loop();
                }
            }).start();
        }
        Response response = chain.proceed(request);
        return response;
    }
}
