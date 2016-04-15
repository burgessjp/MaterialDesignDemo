package ren.solid.materialdesigndemo.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ren.solid.materialdesigndemo.R;
import ren.solid.materialdesigndemo.activity.MainActivity;
import ren.solid.skinloader.base.SkinBaseActivity;

/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:9:40
 */
public abstract class BaseActivity extends SkinBaseActivity {

    public static int mTheme = R.style.BrownTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // setTheme(mTheme);
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResourceID());
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

    protected abstract int setLayoutResourceID();

    protected <T extends View> T customFindViewById(int id) {
        return (T) super.findViewById(id);
    }


    protected void startActivityWithoutExtras(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    protected void startActivityWithExtras(Class<?> clazz, Bundle extras) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(extras);
        startActivity(intent);

    }

    public void changeTheme(int theme) {
        mTheme = theme;
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        overridePendingTransition(0, R.anim.activity_close);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_open, 0);
    }
}
