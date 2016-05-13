package ren.solid.skinloader.statusbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.WindowManager;

/**
 * Created by li on 2015/8/3.
 * 这个类的作用是是设置状态栏的颜色，依赖了SystemBarTintManager类
 */
public class StatusBarBackground {

    private Activity activity;
    private int color;


    public StatusBarBackground(Activity activity, int color) {

        this.activity = activity;
        this.color = color;


    }

    public void setStatusBarbackColor()//记得在布局文件根组件上添加android:fitsSystemWindows="true"
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(activity);
            tintManager.setStatusBarTintEnabled(true);
            // tintManager.setStatusBarTintResource(color);
            tintManager.setStatusBarTintColor(color);
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {

        WindowManager.LayoutParams winParams = activity.getWindow().getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        activity.getWindow().setAttributes(winParams);
    }
}
