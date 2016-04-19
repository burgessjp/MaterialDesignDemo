package ren.solid.materialdesigndemo.fragment;

import android.os.RecoverySystem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TableLayout;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ren.solid.materialdesigndemo.R;
import ren.solid.materialdesigndemo.adapter.GanHuoPagerAdapter;
import ren.solid.materialdesigndemo.constants.Apis;
import ren.solid.materialdesigndemo.fragment.base.BaseFragment;
import ren.solid.materialdesigndemo.utils.CharsetUtils;
import ren.solid.materialdesigndemo.utils.HttpUtils;

/**
 * Created by _SOLID
 * Date:2016/4/18
 * Time:15:30
 */
public class GanHuoFragment extends BaseFragment {
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_gan_huo;
    }

    @Override
    protected void initView() {

        String[] titles = new String[]{"all", "福利", "Android", "iOS", " 休息视频", "拓展资源", "前端"};
        TabLayout mTabLayout = customFindViewById(R.id.sliding_tabs);
        ViewPager mViewPager = customFindViewById(R.id.viewpager);
        mViewPager.setAdapter(new GanHuoPagerAdapter(getChildFragmentManager(), titles));
        for (int i = 0; i < titles.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(titles[i]));
        }
        mTabLayout.setupWithViewPager(mViewPager);


        HttpUtils.getInstance().loadString("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1", new HttpUtils.HttpCallBack() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onSuccess(String result) {
                result = CharsetUtils.decodeUnicode(result);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }


}
