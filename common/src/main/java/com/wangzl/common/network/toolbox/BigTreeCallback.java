package com.wangzl.common.network.toolbox;

import java.io.IOException;

import retrofit2.Response;

/**
 * Created by wangzl on 2016/4/11.
 * A callback which offers granular callbacks for various conditions.
 */
public interface BigTreeCallback<T> {
//    /** Called for [200, 300) responses. */
//    void success(Response<T> response);
//    /** Called for 401 responses. */
//    void unauthenticated(Response<?> response);
//    /** Called for [400, 500) responses, except 401. */
//    void clientError(Response<?> response);
//    /** Called for [500, 600) response. */
//    void serverError(Response<?> response);
//    /** Called for network errors while making the call. */
//    void networkError(IOException e);
//    /** Called for unexpected errors while making the call. */
//    void unexpectedError(Throwable t);

    void onSuccess(Response<T> response);

    void onFailure(Throwable t);
}