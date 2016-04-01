package ren.solid.materialdesigndemo;

import android.app.Application;

/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:20:59
 */
public class SolidApplication extends Application {
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
