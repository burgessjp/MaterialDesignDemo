package ren.solid.materialdesigndemo.activity;

import android.util.Log;

import ren.solid.materialdesigndemo.R;
import ren.solid.materialdesigndemo.activity.base.BaseActivity;

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
