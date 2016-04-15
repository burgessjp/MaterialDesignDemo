package ren.solid.skinloader.base;

import android.app.Application;

import ren.solid.skinloader.load.SkinManager;

/**
 * Created by _SOLID
 * Date:2016/4/14
 * Time:10:54
 */
public class SkinApplication extends Application {

    public void onCreate() {
        super.onCreate();

        initSkinLoader();
    }

    /**
     * Must call init first
     */
    private void initSkinLoader() {
        SkinManager.getInstance().init(this);
        SkinManager.getInstance().load();
    }
}
