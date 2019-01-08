package com.wangzl.common.network.toolbox;

/**
 * Created by wangzl on 2016/6/1.
 * 该接口用于抽象发起网络请求的上下文，可能是Activity或Fragment或其他组件，
 * 用于对同一上下文内的网络请求进行管理
 */
public interface NetworkCallContext {
    /**
     * 添加一个网络请求到当前上下文
     *
     * @param call
     */
    void addNetworkCall(BigTreeCall call);

    /**
     * 取消当前上下文中的所有网络请求，一般在页面销毁时调用
     */
    void cancelAll();
}
