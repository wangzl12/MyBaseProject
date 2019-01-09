package com.wangzl.mybaseproject.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangzl.common.network.toolbox.BigTreeCall;
import com.wangzl.common.network.toolbox.NetworkCallContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangzl
 * @Date 2019/1/9 14:30
 * @Description frgament基类
 */
public class BaseFragment extends Fragment implements NetworkCallContext{
    protected final String TAG = this.getClass().getSimpleName();

    protected List<BigTreeCall> mCallList = new ArrayList<>();

    //是否可见状态
    private boolean isVisible;
    //View已经初始化完成
    private boolean isPrepared;
    //是否第一次加载完
    private boolean isFirstLoad = true;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isFirstLoad = true;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void onInvisible() {
    }

    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirstLoad) {
            return;
        }
        lazyLoadData();
        isFirstLoad = false;
    }

    protected void lazyLoadData() {

    }


    @Override
    public void onDestroyView() {
        cancelAll();
        super.onDestroyView();
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
