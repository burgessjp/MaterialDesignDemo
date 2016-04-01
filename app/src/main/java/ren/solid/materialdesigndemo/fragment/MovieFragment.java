package ren.solid.materialdesigndemo.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ren.solid.materialdesigndemo.R;
import ren.solid.materialdesigndemo.fragment.base.BaseFragment;

/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:11:54
 */
public class MovieFragment extends BaseFragment {

    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    protected void initView() {
        super.initView();
    }
}
