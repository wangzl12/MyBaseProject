package com.wangzl.mybaseproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wangzl.mybaseproject.base.BaseActivity;
import com.wangzl.mybaseproject.find.FindFragment;
import com.wangzl.mybaseproject.home.HomeFragment;
import com.wangzl.mybaseproject.mine.MineFragment;
import com.wangzl.mybaseproject.product.ProductFragment;
import com.wangzl.mybaseproject.view.MyNoScrollViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 程序默认进入页面
 */
public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private static final int TAB_PAGE_COUNT = 4;

    @BindView(R.id.view_pager_main)
    MyNoScrollViewPager viewPagerMain;
    @BindView(R.id.tv_main_tab_financial)
    TextView tvMainTabFinancial;
    @BindView(R.id.ll_main_tab_finacial)
    LinearLayout llMainTabFinacial;
    @BindView(R.id.tv_main_tab_loan)
    TextView tvMainTabLoan;
    @BindView(R.id.ll_main_tab_loan)
    LinearLayout llMainTabLoan;
    @BindView(R.id.tv_main_tab_assets)
    TextView tvMainTabAssets;
    @BindView(R.id.ll_main_tab_assets)
    LinearLayout llMainTabAssets;
    @BindView(R.id.tv_main_tab_person)
    TextView tvMainTabPerson;
    @BindView(R.id.img_my_visible)
    ImageView imgMyVisible;
    @BindView(R.id.ll_main_tab_person)
    RelativeLayout llMainTabPerson;


    private int mTabTextColorSelecdted;
    private int mTabTextColorNormal;

    private HomeFragment mHomeFragment;
    private FindFragment mFindFragment;
    private ProductFragment mProductFragment;
    private MineFragment mMineFragment;

    private MainPagerAdapter mMainPagerAdapter;
    private MainOnPageChangeListener mMainOnPageChangeListener;

    int currentPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mTabTextColorSelecdted = getResources().getColor(R.color.main_tab_text_selected);
        mTabTextColorNormal = getResources().getColor(R.color.main_tab_text_normal);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getFragments() != null && fragmentManager.getFragments().size() > 0) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            List<Fragment> fragments = fragmentManager.getFragments();
            for (Fragment f : fragments) {
                if (f != null) {
                    transaction.remove(f);
                }
            }
            transaction.commitAllowingStateLoss();
        }

        mMainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mMainOnPageChangeListener = new MainOnPageChangeListener();
        mHomeFragment = HomeFragment.newInstance();
        mProductFragment = ProductFragment.newInstance();
        mFindFragment = FindFragment.newInstance();
        mMineFragment = MineFragment.newInstance();
        viewPagerMain.setAdapter(mMainPagerAdapter);
        viewPagerMain.setOnPageChangeListener(mMainOnPageChangeListener);
        viewPagerMain.setOffscreenPageLimit(3);
    }

    @OnClick({R.id.ll_main_tab_finacial, R.id.ll_main_tab_loan, R.id.ll_main_tab_assets, R.id.ll_main_tab_person})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_main_tab_finacial:
                currentPos = 0;
                break;
            case R.id.ll_main_tab_loan:
                currentPos = 1;
                break;
            case R.id.ll_main_tab_assets:
                currentPos = 2;
                break;
            case R.id.ll_main_tab_person:
                currentPos = 3;
                break;
        }
        updateTabsSelected(currentPos);
        viewPagerMain.setCurrentItem(currentPos, false);
    }


    private class MainOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int pos) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int pos) {
            updateTabsSelected(pos);
        }
    }


    private void updateTabsNormal() {
        tvMainTabAssets.setTextColor(mTabTextColorNormal);
        tvMainTabAssets.setCompoundDrawablesWithIntrinsicBounds(null,
                getResources().getDrawable(R.mipmap.tabbar_trade_normal), null, null);

        tvMainTabLoan.setTextColor(mTabTextColorNormal);
        tvMainTabLoan.setCompoundDrawablesWithIntrinsicBounds(null,
                getResources().getDrawable(R.mipmap.tabbar_borrow_normal), null, null);

        tvMainTabFinancial.setTextColor(mTabTextColorNormal);
        tvMainTabFinancial.setCompoundDrawablesWithIntrinsicBounds(null,
                getResources().getDrawable(R.mipmap.tabbar_management_normal), null, null);

        tvMainTabPerson.setTextColor(mTabTextColorNormal);
        tvMainTabPerson.setCompoundDrawablesWithIntrinsicBounds(null,
                getResources().getDrawable(R.mipmap.tabbar_me_normal), null, null);

    }

    public void updateTabsSelected(int currentPox) {
        updateTabsNormal();
        switch (currentPox) {
            case 0:
                tvMainTabFinancial.setTextColor(mTabTextColorSelecdted);
                tvMainTabFinancial.setCompoundDrawablesWithIntrinsicBounds(null,
                        getResources().getDrawable(R.mipmap.tabbar_management_selected), null, null);
                break;
            case 1:
                tvMainTabLoan.setTextColor(mTabTextColorSelecdted);
                tvMainTabLoan.setCompoundDrawablesWithIntrinsicBounds(null,
                        getResources().getDrawable(R.mipmap.tabbar_borrow_selected), null, null);
                break;
            case 2:
                tvMainTabAssets.setTextColor(mTabTextColorSelecdted);
                tvMainTabAssets.setCompoundDrawablesWithIntrinsicBounds(null,
                        getResources().getDrawable(R.mipmap.tabbar_trade_selected), null, null);
                break;
            case 3:
                tvMainTabPerson.setTextColor(mTabTextColorSelecdted);
                tvMainTabPerson.setCompoundDrawablesWithIntrinsicBounds(null,
                        getResources().getDrawable(R.mipmap.tabbar_me_selected), null, null);
                break;

            default:
                break;
        }
    }


    private class MainPagerAdapter extends FragmentPagerAdapter {

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return TAB_PAGE_COUNT;
        }

        @Override
        public Fragment getItem(final int position) {
            switch (position) {
                case 0:
                    return MainActivity.this.mHomeFragment;
                case 1:
                    return MainActivity.this.mProductFragment;
                case 2:
                    return MainActivity.this.mFindFragment;
                case 3:
                    return MainActivity.this.mMineFragment;
                default:
                    return MainActivity.this.mHomeFragment;
            }
        }
    }
}
