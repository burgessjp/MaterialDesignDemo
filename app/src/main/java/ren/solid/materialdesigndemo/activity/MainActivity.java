package ren.solid.materialdesigndemo.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import ren.solid.materialdesigndemo.R;
import ren.solid.materialdesigndemo.activity.base.BaseActivity;
import ren.solid.materialdesigndemo.fragment.BlogFragment;
import ren.solid.materialdesigndemo.fragment.MainFragment;
import ren.solid.materialdesigndemo.fragment.base.WebViewFragment;
import ren.solid.materialdesigndemo.utils.ViewUtils;

public class MainActivity extends BaseActivity {


    private DrawerLayout mDrawerLayout;//侧边菜单视图
    private ActionBarDrawerToggle mDrawerToggle;  //菜单开关
    private Toolbar mToolbar;
    private NavigationView mNavigationView;//侧边菜单项

    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;

    @Override
    protected int setLayoutResourseID() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);

        mToolbar.setTitle("首页");
        //这句一定要在下面几句之前调用，不然就会出现点击无反应
        setSupportActionBar(mToolbar);
        //ActionBarDrawerToggle配合Toolbar，实现Toolbar上菜单按钮开关效果。
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setNavigationViewItemClickListener();

        initDefaultFragment();
    }


    //初始化默认选中的Fragment
    private void initDefaultFragment() {
        mCurrentFragment = ViewUtils.createFrgment("MainFragment");
        mFragmentManager.beginTransaction().add(R.id.frame_content, mCurrentFragment).commit();
    }

    private void setNavigationViewItemClickListener() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_item_home:
                        mToolbar.setTitle("首页");
                        switchFragment("MainFragment");
                        break;
                    case R.id.navigation_item_blog:
                        mToolbar.setTitle("我的博客");
                        switchFragment("BlogFragment");
                        break;
                    case R.id.navigation_item_about:
                        mToolbar.setTitle("关于");
                        switchFragment("AboutFragment");
                        break;
                    default:
                        break;
                }
                item.setChecked(true);
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                return false;
            }
        });
    }

    //切换Fragment
    private void switchFragment(String fName) {
        Fragment to = ViewUtils.createFrgment(fName);
        if (to.isAdded()) {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).show(to).commit();
        } else {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).add(R.id.frame_content, to).commit();
        }
        mCurrentFragment = to;
    }

    private long lastBackKeyDownTick = 0;
    public static final long MAX_DOUBLE_BACK_DURATION = 1500;

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
            return;
        }

        if (mCurrentFragment instanceof WebViewFragment) {//如果当前的Fragment是WebViewFragment 则监听返回事件
            WebViewFragment webViewFragment = (WebViewFragment) mCurrentFragment;
            if (webViewFragment.canGoBack()) {
                webViewFragment.goBack();
                return;
            }
        }

        long currentTick = System.currentTimeMillis();
        if (currentTick - lastBackKeyDownTick > MAX_DOUBLE_BACK_DURATION) {
            Snackbar.make(mDrawerLayout, "再按一次退出", Snackbar.LENGTH_SHORT).show();
            lastBackKeyDownTick = currentTick;
        } else {
            finish();
        }
    }
}
