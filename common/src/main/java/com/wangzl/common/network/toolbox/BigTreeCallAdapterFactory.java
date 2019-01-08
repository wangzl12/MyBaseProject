package com.wangzl.common.network.toolbox;

import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * Created by wangzl on 2016/4/11.
 */

public class BigTreeCallAdapterFactory extends CallAdapter.Factory {
    @Override public CallAdapter<BigTreeCall<?>> get(Type returnType, Annotation[] annotations,
                                                     Retrofit retrofit) {
        TypeToken<?> token = TypeToken.get(returnType);
        if (token.getRawType() != BigTreeCall.class) {
            return null;
        }
        if (!(returnType instanceof ParameterizedType)) {
            throw new IllegalStateException(
                    "BigTreeCall must have generic type (e.g., BigTreeCall<ResponseBody>)");
        }
        final Type responseType = ((ParameterizedType) returnType).getActualTypeArguments()[0];
        return new CallAdapter<BigTreeCall<?>>() {
            @Override public Type responseType() {
                return responseType;
            }

            @Override public <R> BigTreeCall<R> adapt(Call<R> call) {
                return new BigTreeCallAdapter<>(call);
            }
        };
    }
}