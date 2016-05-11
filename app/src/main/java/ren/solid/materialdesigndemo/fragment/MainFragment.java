package ren.solid.materialdesigndemo.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.materialdesigndemo.R;
import ren.solid.materialdesigndemo.adapter.FindPagerAdapter;

/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:11:29
 */
public class MainFragment extends BaseFragment {

    private static String TAG = "MainFragment";
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_main;
    }

    @Override
    protected void setUpView() {
        mTabLayout = $(R.id.sliding_tabs);
        mViewPager = $(R.id.viewpager);


        List<String> titles = new ArrayList<>();
        titles.add("找书籍");
        //titles.add("找电影");

        FindPagerAdapter viewPagerAdapter = new FindPagerAdapter(getMContext(), titles, getChildFragmentManager());
        mViewPager.setAdapter(viewPagerAdapter);

        // because the movie module not completed,so cancel the TabLayout temporary.

        mTabLayout.setVisibility(View.GONE);
        // mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        // mTabLayout.addTab(mTabLayout.newTab().setText("读书"));
        // mTabLayout.addTab(mTabLayout.newTab().setText("电影"));
        //mTabLayout.setupWithViewPager(mViewPager);

        dynamicAddSkinView(mTabLayout, "tabIndicatorColor", R.color.colorAccent);
    }
}
