package com.wangzl.common.network;

import com.wangzl.common.model.bean.homebean.IndexBean;
import com.wangzl.common.network.toolbox.BigTreeCall;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @Author wangzl
 * @Date 2019/1/8 15:47
 * @Description TODO
 */
public class Api {

    //首页数据
    public interface IndexApi {
        @FormUrlEncoded
        @POST(ApiUrl.API_INDEX)
        BigTreeCall<IndexBean> getIndex(@Field("type") String type,
                                        @Field("key") String key);
    }

}
