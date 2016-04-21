package ren.solid.materialdesigndemo.fragment;

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
    protected int setLayoutResourceID() {
        return R.layout.fragment_custom_view;
    }

    @Override
    protected void initView() {
        super.initView();
        QQHealthView qqHealthView = customFindViewById(R.id.qqhealthview);
        qqHealthView.setSteps(new int[]{5025, 15280, 8900, 9200, 6500, 5660, 9450});
        // qqHealthView.setThemeColor(Color.parseColor("#87CEEB"));

    }
}
