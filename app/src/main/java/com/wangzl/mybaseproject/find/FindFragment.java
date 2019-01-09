package com.wangzl.mybaseproject.find;

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
public class FindFragment extends BaseFragment {


    public static FindFragment newInstance() {
        FindFragment fragment = new FindFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        return view;
    }

}
