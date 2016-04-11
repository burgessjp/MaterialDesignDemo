package ren.solid.materialdesigndemo.fragment;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ren.solid.materialdesigndemo.R;
import ren.solid.materialdesigndemo.fragment.base.BaseFragment;
import ren.solid.materialdesigndemo.view.QQHealthView;

/**
 * Created by _SOLID
 * Date:2016/4/10
 * Time:12:54
 */
public class CustomViewFragment extends BaseFragment {

    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_custom_view, container, false);
        QQHealthView qqHealthView = (QQHealthView) view.findViewById(R.id.qqhealthview);
        qqHealthView.setSteps(new int[]{100, 15280, 8900, 9200, 6500, 5660, 9450});
      //  qqHealthView.setThemeColor(Color.parseColor("#00000f"));
        return view;
    }
}
