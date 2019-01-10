package com.wangzl.mybaseproject.home.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wangzl.common.model.bean.homebean.IndexBean;
import com.wangzl.common.network.NetworkManager;
import com.wangzl.common.network.toolbox.BigTreeCallback;
import com.wangzl.mybaseproject.R;
import com.wangzl.mybaseproject.base.BaseFragment;
import com.wangzl.mybaseproject.home.NewsDetailActivity;
import com.yuyh.easyadapter.recyclerview.EasyRVAdapter;
import com.yuyh.easyadapter.recyclerview.EasyRVHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Response;

/**
 * @Author wangzl
 * @Date 2019/1/10 14:04
 * @Description TODO
 */
@SuppressLint("ValidFragment")
public class InfomationListFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.news_recycleView)
    RecyclerView newsRecycleView;
    private String type;

    private EasyRVAdapter mEasyAdapter;

    public InfomationListFragment(String type) {
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.infomation_list_fragment_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        initData();
        return view;
    }

    private void initData() {
        Log.e(TAG,"========="+type);
        NetworkManager.getSingleton().fetchIndex(type, new BigTreeCallback<IndexBean>() {
            @Override
            public void onSuccess(Response<IndexBean> response) {
                IndexBean indexBean = response.body();
                List<IndexBean.ResultBean.DataBean> newsList =  indexBean.getResult().getData();
                showNewsList(newsList);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        }).with(this);
    }

    private void showNewsList(final List<IndexBean.ResultBean.DataBean> newsList) {
        mEasyAdapter = new EasyRVAdapter(getActivity(),newsList,R.layout.news_list_item) {
            @Override
            protected void onBindData(EasyRVHolder viewHolder, int position, Object item) {
                IndexBean.ResultBean.DataBean bean = (IndexBean.ResultBean.DataBean) item;
                TextView tv_news_title = viewHolder.getView(R.id.tv_news_title);
                ImageView iv_news = viewHolder.getView(R.id.iv_news);
                tv_news_title.setText(bean.getTitle());
                Glide.with(getActivity()).load(bean.getThumbnail_pic_s()).into(iv_news);
            }

        };

        mEasyAdapter.setOnItemClickListener(new EasyRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Object item) {
                NewsDetailActivity.startActivity(getActivity(),newsList.get(position).getUrl());

            }
        });

        newsRecycleView.setAdapter(mEasyAdapter);

    }

    private void initViews() {
        newsRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
