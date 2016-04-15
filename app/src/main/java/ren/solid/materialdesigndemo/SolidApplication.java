package ren.solid.materialdesigndemo;

import android.app.Application;

import ren.solid.skinloader.base.SkinApplication;

/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:20:59
 */
public class SolidApplication extends SkinApplication {
    private static SolidApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static SolidApplication getInstance() {
        return mInstance;
    }
}
