package com.wangzl.mybaseproject.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.wangzl.mybaseproject.R;
import com.wangzl.mybaseproject.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author wangzl
 * @Date 2019/1/10 17:28
 * @Description TODO
 */
public class NewsDetailActivity extends BaseActivity {

    public static void startActivity(Context context,String url) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }


    @BindView(R.id.my_webview)
    WebView myWebview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        Intent mIntent = getIntent();
        String url = mIntent.getStringExtra("url");
        myWebview.loadUrl(url);
    }
}
