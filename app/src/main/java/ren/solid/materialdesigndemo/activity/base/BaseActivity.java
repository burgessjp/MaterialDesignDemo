package ren.solid.materialdesigndemo.activity.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Window;

/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:9:40
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResourseID());
        init();
        initView();
        initData();
    }

    protected void initData() {

    }

    /***
     * 用于在初始化View之前做一些事
     */
    protected void init() {

    }

    protected abstract void initView();

    protected abstract int setLayoutResourseID();
}
