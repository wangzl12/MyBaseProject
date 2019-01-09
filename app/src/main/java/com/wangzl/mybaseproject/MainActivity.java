package com.wangzl.mybaseproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.wangzl.common.model.bean.homebean.IndexBean;
import com.wangzl.common.network.NetworkManager;
import com.wangzl.common.network.toolbox.BigTreeCall;
import com.wangzl.common.network.toolbox.BigTreeCallback;
import com.wangzl.common.network.toolbox.NetworkCallContext;
import com.wangzl.mybaseproject.base.BaseActivity;

import retrofit2.Response;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    private void initData() {
        String type = "top";
        String key = "9edd8a27f78be6235f319be7cfd68c41";

        NetworkManager.getSingleton().fetchIndex(type, new BigTreeCallback<IndexBean>() {
            @Override
            public void onSuccess(Response<IndexBean> response) {
                IndexBean data = response.body();
                Log.e("Main======", data.getResult().getData().get(0).getAuthor_name());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        }).with(mContext);
    }

}
