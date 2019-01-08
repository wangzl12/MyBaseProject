package com.wangzl.common.network.toolbox;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.concurrent.Executor;

import retrofit2.Response;

/**
 * Created by wangzl on 2016/4/12.
 */
public class ExecutorDelivery {
    private final Executor mResponsePoster;

    private static ExecutorDelivery mMainThreadDelivery;

    public ExecutorDelivery(final Handler handler) {
        this.mResponsePoster = new Executor() {
            public void execute(Runnable command) {
                handler.post(command);
            }
        };
    }

    public static ExecutorDelivery getMainThreadDelivery() {
        if (mMainThreadDelivery == null) {
            mMainThreadDelivery = new ExecutorDelivery(new Handler(Looper.getMainLooper()));
        }
        return mMainThreadDelivery;
    }

    public void postResponse(Response<?> response, BigTreeCallback<?> callback) {
        this.mResponsePoster.execute(new ResponseDeliveryRunnable(response, callback));
    }

    public void postError(Throwable t, BigTreeCallback<?> callback) {
        this.mResponsePoster.execute(new ErrorDeliveryRunnable(t, callback));
    }

    private class ResponseDeliveryRunnable implements Runnable {
        private final Response response;
        private final BigTreeCallback<?> callback;

        public ResponseDeliveryRunnable(Response response, BigTreeCallback<?> callback) {
            this.response = response;
            this.callback = callback;
        }

        public void run() {
            int code = response.code();
            if (code >= 200 && code < 300) {
                callback.onSuccess(response);
                return;
            }

            if (code == 401) {
                unauthenticated(response);
            } else if (code >= 400 && code < 500) {
                clientError(response);
            } else if (code >= 500 && code < 600) {
                serverError(response);
            } else {
                unexpectedError(new RuntimeException("Unexpected response " + response));
            }
            callback.onFailure(new Throwable(response.message()));
        }
    }

    private class ErrorDeliveryRunnable implements Runnable {
        private final Throwable t;
        private final BigTreeCallback<?> callback;

        public ErrorDeliveryRunnable(Throwable throwable, BigTreeCallback<?> callback) {
            this.t = throwable;
            this.callback = callback;
        }

        public void run() {
            if (t instanceof IOException) {
                networkError((IOException) t);
            } else {
                unexpectedError(t);
            }
            callback.onFailure(t);
        }
    }

    //底层错误统一处理
    private void unauthenticated(Response<?> response) {

    }

    private void clientError(Response<?> response) {

    }

    private void serverError(Response<?> response) {

    }

    private void unexpectedError(RuntimeException e) {

    }

    private void networkError(IOException e) {

    }

    private void unexpectedError(Throwable t) {

    }
}
