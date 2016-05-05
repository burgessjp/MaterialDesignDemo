package ren.solid.materialdesigndemo.activity;

import android.util.Log;

import ren.solid.library.activity.base.BaseActivity;
import ren.solid.materialdesigndemo.R;

public class SettingActivity extends BaseActivity {


    @Override
    protected void initView() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_setting;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("SettingActivity","onDestroy");
    }
}
