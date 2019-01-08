package com.wangzl.common.network.toolbox;

import android.util.Log;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wangzl on 2016/4/11.
 * Adapts a {@link Call} to {@link BigTreeCall}.
 */
public class BigTreeCallAdapter<T> implements BigTreeCall<T> {
    private final Call<T> call;

    public BigTreeCallAdapter(Call<T> call) {
        this.call = call;
    }

    @Override
    public void with(NetworkCallContext context) {
        context.addNetworkCall(this);
    }

    @Override
    public void cancel() {
        call.cancel();
        Log.i("calltest", "call is canceled, call = " + call.toString());
    }

    @Override
    public void enqueue(final BigTreeCallback<T> callback) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                ExecutorDelivery.getMainThreadDelivery().postResponse(response, callback);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                ExecutorDelivery.getMainThreadDelivery().postError(t, callback);
            }
        });
    }

    @Override
    public boolean isCanceled() {
        return call.isCanceled();
    }

    @Override
    public BigTreeCall<T> clone() {
        return new BigTreeCallAdapter<>(call.clone());
    }
}
