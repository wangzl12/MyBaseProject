package com.wangzl.mybaseproject.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.wangzl.mybaseproject.R;
import com.wangzl.mybaseproject.base.BaseFragment;

/**
 * @Author wangzl
 * @Date 2019/1/9 17:06
 * @Description TODO
 */
public class MineFragment extends BaseFragment {
    private SlidingMenu slidingMenu;
    private Button mBtn;


    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        initSlidingMenu();
        return view;
    }

    private void initSlidingMenu() {
        slidingMenu = new SlidingMenu(getActivity());
        slidingMenu.setMode(SlidingMenu.RIGHT);  ////设置从右弹出/滑出SlidingMenu
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN); ////设置占满屏幕
        slidingMenu.attachToActivity(getActivity(),SlidingMenu.SLIDING_CONTENT);//绑定到哪一个Activity对象
        slidingMenu.setMenu(R.layout.sliding_menu_layout);//设置弹出的SlidingMenu的布局文件
        slidingMenu.setBehindOffset(120);//设置SlidingMenu所占的偏移
        slidingMenu.setBehindWidth(600); //设置宽度
        slidingMenu.setFadeDegree(0.35f);// 设置渐入渐出效果的值

        mBtn= slidingMenu.findViewById(R.id.btn_click);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingMenu.toggle();
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();

    }
}
