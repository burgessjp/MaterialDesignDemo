package ren.solid.materialdesigndemo.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import ren.solid.library.fragment.StringFragment;
import ren.solid.library.utils.ViewUtils;
import ren.solid.materialdesigndemo.bean.BookBean;

/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:21:51
 */
public class BookInfoPageAdapter extends FragmentPagerAdapter {

    private final List<String> mTitleList;
    private final Context mContext;
    private BookBean mBookBean;

    public BookInfoPageAdapter(Context context, BookBean bookBean, FragmentManager fm) {
        super(fm);
        mContext = context;
        mBookBean = bookBean;
        mTitleList = new ArrayList<>();
        mTitleList.add("作者信息");
        mTitleList.add("章节");
        mTitleList.add("书籍简介");
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = ViewUtils.createFragment(StringFragment.class, false);
        Bundle bundle = new Bundle();
        if (getPageTitle(position).equals("作者信息")) {
            bundle.putString("text", mBookBean.getAuthor_intro());
        } else if (getPageTitle(position).equals("章节")) {
            bundle.putString("text", mBookBean.getCatalog());
        } else if (getPageTitle(position).equals("书籍简介")) {
            bundle.putString("text", mBookBean.getSummary());
        }
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return mTitleList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
