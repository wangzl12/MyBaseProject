package com.wangzl.common.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.wangzl.common.AppContextWrapper;

/**
 * ToastUtil 工具类
 *
 * @author wangzl
 * @version 1.0
 */
public class ToastUtil {

    private static Toast mToast;

    /**
     * 显示简单文本信息
     *
     * @param msg
     */
    public static final void showMsg(final String msg) {
        if(TextUtils.isEmpty(msg)){
            return;
        }

        if (mToast == null) {

            try {
                mToast = Toast.makeText(AppContextWrapper.getSingleton().getApp(), msg, Toast.LENGTH_SHORT);
            }catch (Exception ex){
                ex.printStackTrace();
                Log.e(ToastUtil.class.getSimpleName(), "Show toast failed: "+ msg+ "\n"+ ex.getMessage());
                return;
            }
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_LONG);
        }

        mToast.show();
    }

    /**
     * 取消显示
     */
    public static final void cancelMsg() {

        if (mToast != null) {
            mToast.cancel();
        }
    }


    public static final void showMsg(Context context, final String msg) {
        if(TextUtils.isEmpty(msg)){
            return;
        }

        if (mToast == null) {

            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_LONG);
        }

        mToast.show();
    }
}
