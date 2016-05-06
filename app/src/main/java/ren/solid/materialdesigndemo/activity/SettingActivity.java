package ren.solid.materialdesigndemo.activity;

import android.support.v4.app.Fragment;

import ren.solid.library.activity.ToolbarActivity;
import ren.solid.materialdesigndemo.fragment.AboutFragment;

public class SettingActivity extends ToolbarActivity {


    @Override
    protected String getToolbarTitle() {
        return "设置";
    }

    @Override
    protected Fragment setFragment() {
        return new AboutFragment();
    }
}
