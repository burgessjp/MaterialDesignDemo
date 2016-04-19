package ren.solid.materialdesigndemo.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ren.solid.materialdesigndemo.fragment.GanHuoListFragment;
import ren.solid.materialdesigndemo.utils.ViewUtils;

/**
 * Created by _SOLID
 * Date:2016/4/18
 * Time:17:04
 */
public class GanHuoPagerAdapter extends FragmentStatePagerAdapter {

    private static String[] mTitles;

    public GanHuoPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = ViewUtils.createFragment(GanHuoListFragment.class, false);
        Bundle bundle = new Bundle();
        bundle.putString("type", mTitles[position]);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
