package ren.solid.materialdesigndemo.fragment;

import ren.solid.materialdesigndemo.fragment.base.WebViewFragment;

/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:20:03
 */
public class AboutFragment extends WebViewFragment {

    @Override
    public String getLoadUrl() {
        return "file:///android_asset/about.htm";
    }
}
