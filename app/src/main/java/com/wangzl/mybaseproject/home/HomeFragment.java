package com.wangzl.mybaseproject.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.wangzl.mybaseproject.R;
import com.wangzl.mybaseproject.base.BaseFragment;
import com.wangzl.mybaseproject.home.fragment.InfomationListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @Author wangzl
 * @Date 2019/1/9 17:06
 * @Description TODO
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.smart_tab_layout)
    SmartTabLayout smartTabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    Unbinder unbinder;

    private MyAdapter myAdapter;

    private List<String> typeList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private List<InfomationListFragment> fragmentsList = new ArrayList<>();


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    @Override
    protected void onVisible() {
        super.onVisible();
    }

    private void initData() {
        //类型
        typeList.add("top");
        typeList.add("shehui");
        typeList.add("guonei");
        typeList.add("guoji");
        typeList.add("yule");
        typeList.add("tiyu");
        typeList.add("junshi");
        typeList.add("keji");
        typeList.add("caijing");
        typeList.add("shishang");
        //标题
        titleList.add("头条");
        titleList.add("社会");
        titleList.add("国内");
        titleList.add("国际");
        titleList.add("娱乐");
        titleList.add("体育");
        titleList.add("军事");
        titleList.add("科技");
        titleList.add("财经");
        titleList.add("时尚");
        initViews();
    }

    private void initViews() {
        //首先添加fragment
        for (int i = 0; i < typeList.size(); i++) {
            InfomationListFragment fragment = new InfomationListFragment(typeList.get(i));
            fragmentsList.add(fragment);
        }
        myAdapter = new MyAdapter(getChildFragmentManager());
        smartTabLayout.setCustomTabView(R.layout.layout_custom_tab, R.id.custom_tab_text);
        viewpager.setAdapter(myAdapter);
        smartTabLayout.setViewPager(viewpager);
        smartTabLayout.setOnPageChangeListener(mChangeTabTextColorListener);
        viewpager.setCurrentItem(0);

        View firstTab = smartTabLayout.getTabAt(0);
        if (firstTab instanceof ViewGroup) {
            TextView tabText = (TextView) ((ViewGroup) firstTab).getChildAt(1);
            tabText.setTextColor(ContextCompat.getColor(getContext(), R.color.bg_corner_bule));
        }
    }


    /**
     * 文字随指示器变色监听器
     */
    private ViewPager.SimpleOnPageChangeListener mChangeTabTextColorListener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            int childCount = myAdapter.getCount();
            for (int i = 0; i < childCount; i++) {
                View tab = smartTabLayout.getTabAt(i);
                if (tab instanceof ViewGroup) {
                    TextView tabText = (TextView) ((ViewGroup) tab).getChildAt(1);
                    if (i == position) {
                        tabText.setTextColor(ContextCompat.getColor(getContext(), R.color.bg_corner_bule));
                    } else {
                        tabText.setTextColor(ContextCompat.getColor(getContext(), R.color.common_text_color_99));
                    }
                }
            }
        }
    };


    private class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentsList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentsList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String titleStr = titleList.get(position);
            return titleStr;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
