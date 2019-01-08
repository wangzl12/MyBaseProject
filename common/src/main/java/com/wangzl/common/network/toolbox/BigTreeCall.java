package com.wangzl.common.network.toolbox;

/**
 * Created by wangzl on 2016/4/11.
 */
public interface BigTreeCall<T> {
    void cancel();
    void enqueue(BigTreeCallback<T> callback);
    boolean isCanceled();
    BigTreeCall<T> clone();
    void with(NetworkCallContext context);
    // Left as an exercise for the reader...
    // TODO MyResponse<T> execute() throws MyHttpException;
}
