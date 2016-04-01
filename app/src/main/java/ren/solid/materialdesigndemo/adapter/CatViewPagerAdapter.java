package ren.solid.materialdesigndemo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import ren.solid.materialdesigndemo.fragment.BookFragment;
import ren.solid.materialdesigndemo.fragment.MovieFragment;

/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:11:48
 */
public class CatViewPagerAdapter extends FragmentPagerAdapter {


    private final List<String> mTitleList;
    private final Context mContext;

    public CatViewPagerAdapter(Context context, List<String> titles, FragmentManager fm) {
        super(fm);
        mContext = context;
        mTitleList = titles;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = createFrgmentByTitle(mTitleList.get(position));
        return fragment;
    }

    @Override
    public int getCount() {
        return mTitleList.size();
    }

    private Fragment createFrgmentByTitle(String title) {

        Fragment result = new BookFragment();//这里主要是一个防止没有找到Fragment，给一个默认
        if (title.equals("找书籍")) {
            result = new BookFragment();
        } else if (title.equals("找电影")) {
            result = new MovieFragment();
        }
        return result;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
