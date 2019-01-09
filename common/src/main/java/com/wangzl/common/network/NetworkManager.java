package com.wangzl.common.network;

import android.util.Log;

import com.wangzl.common.model.bean.homebean.IndexBean;
import com.wangzl.common.network.cookie.CookiesManager;
import com.wangzl.common.network.interceptor.BasicParamsInterceptor;
import com.wangzl.common.network.interceptor.LoggerInterceptor;
import com.wangzl.common.network.interceptor.NoNetInterceptor;
import com.wangzl.common.network.toolbox.BigTreeCall;
import com.wangzl.common.network.toolbox.BigTreeCallAdapterFactory;
import com.wangzl.common.network.toolbox.BigTreeCallback;
import com.wangzl.common.utils.GsonHelper;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author wangzl
 * @Date 2019/1/8 15:18
 * @Description 网络管理类，管理所有网络请求的方法
 */
public class NetworkManager {

    public static String TAG = "NetworkManager";
    public static final String BASE_URL = "http://v.juhe.cn/toutiao/";//主Api路径

    private static final long CONNECTION_TIMEOUT = 5 * 60 * 1000l;
    private static volatile NetworkManager singleton;
    private Retrofit mRetrofit;

    private ConcurrentHashMap<Class, Object> mApiCache = new ConcurrentHashMap<>();


    public void getBaseUrl(final OkHttpClient.Builder builder) {
        //TODO 开发过程中可能切换环境

        mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(builder.build())
                .addCallAdapterFactory(new BigTreeCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(GsonHelper.getSingleton()))
                .build();
    }


    public static void init() {
        if (singleton == null) {
            synchronized (NetworkManager.class) {
                if (singleton == null) {
                    singleton = new NetworkManager();
                    singleton.initInternal();
                }
            }
        }
    }

    public static NetworkManager getSingleton() {
        return singleton;
    }

    /**
     * 添加公共参数的拦截器
     *
     * @return
     */
    public static BasicParamsInterceptor addParamsInterceptor() {
        BasicParamsInterceptor basicParamsInterceptor = new BasicParamsInterceptor.Builder()
                .addParam("key", "9edd8a27f78be6235f319be7cfd68c41")
                .build();
        return basicParamsInterceptor;
    }

    private void initInternal() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cookieJar(new CookiesManager());
        builder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS);
        builder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS);
        builder.writeTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS);
        builder.addInterceptor(new LoggerInterceptor());
        builder.addInterceptor(new NoNetInterceptor());
        builder.addInterceptor(addParamsInterceptor());
        getBaseUrl(builder);
    }

    public Object getApi(Class clazz) {
        Object api = mApiCache.get(clazz);
        if (api == null) {
            api = mRetrofit.create(clazz);
            mApiCache.put(clazz, api);
        }
        return api;
    }


    //用于业务层调用的方法统一在这里添加


    //1.获取首页数据
    public BigTreeCall fetchIndex(String type, BigTreeCallback<IndexBean> callback) {
        Api.IndexApi api = (Api.IndexApi) getApi(Api.IndexApi.class);
        BigTreeCall<IndexBean> call = api.getIndex(type);
        call.enqueue(callback);
        return call;
    }


}
