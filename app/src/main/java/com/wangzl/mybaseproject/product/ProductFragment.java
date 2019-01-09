package com.wangzl.mybaseproject.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangzl.mybaseproject.R;
import com.wangzl.mybaseproject.base.BaseFragment;

/**
 * @Author wangzl
 * @Date 2019/1/9 17:06
 * @Description TODO
 */
public class ProductFragment extends BaseFragment {


    public static ProductFragment newInstance() {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        return view;
    }

}
