package ren.solid.materialdesigndemo.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import java.util.HashMap;
import java.util.Map;

import ren.solid.materialdesigndemo.fragment.AboutFragment;
import ren.solid.materialdesigndemo.fragment.BlogFragment;
import ren.solid.materialdesigndemo.fragment.BookFragment;
import ren.solid.materialdesigndemo.fragment.MainFragment;
import ren.solid.materialdesigndemo.fragment.MovieFragment;
import ren.solid.materialdesigndemo.fragment.base.BaseFragment;

/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:19:37
 */
public class ViewUtils {

    public static final int INT = 21;
    public static final int INT1 = 4;
    public static final int INT2 = 5;
    private static Map<String, BaseFragment> fragmentList = new HashMap<>();

//    /**
//     * 根据名字创建Fragment
//     *
//     * @param fName
//     * @return
//     */
//    public static BaseFragment createFrgment(String fName) {
//        BaseFragment resultFragment = null;
//        if (fragmentList.containsKey(fName)) {
//            resultFragment = fragmentList.get(fName);
//        } else {
//            if (fName.equals("BlogFragment")) {
//                resultFragment = new BlogFragment();
//            } else if (fName.equals("MainFragment")) {
//                resultFragment = new MainFragment();
//            } else if (fName.equals("BookFragment")) {
//                resultFragment = new BookFragment();
//            } else if (fName.equals("MovieFragment")) {
//                resultFragment = new MovieFragment();
//            } else if (fName.equals("AboutFragment")) {
//                resultFragment = new AboutFragment();
//            }
//
//            fragmentList.put(fName, resultFragment);
//        }
//
//        return resultFragment;
//    }

    /**
     * 根据Class创建Fragment
     *
     * @param clazz
     * @return
     */
    public static BaseFragment createFrgment(Class<?> clazz, boolean isObtain) {
        BaseFragment resultFragment = null;
        String className = clazz.getName();
        if (fragmentList.containsKey(className)) {
            resultFragment = fragmentList.get(className);
        } else {
            try {
                try {
                    resultFragment = (BaseFragment) Class.forName(className).newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (isObtain)
                fragmentList.put(className, resultFragment);
        }

        return resultFragment;
    }

    public static BaseFragment createFrgment(Class<?> clazz) {
        return createFrgment(clazz, true);
    }


    /**
     * 获取屏幕的宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    /**
     * 获取屏幕的高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    //转换dp为px
    public static int dp2px(Context context, int dip) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
    }

    //转换px为dp
    public static int px2dp(Context context, int px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f * (px >= 0 ? 1 : -1));
    }

    //转换sp为px
    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    //转换px为sp
    public static int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int getThemeColorPrimary(Context ctx) {
        TypedValue typedValue = new TypedValue();
        ctx.getTheme().resolveAttribute(android.R.attr.theme, typedValue, true);
        int[] attribute = new int[]{android.R.attr.colorPrimary};
        TypedArray array = ctx.obtainStyledAttributes(typedValue.resourceId, attribute);
        int color = array.getColor(0, -1);
        array.recycle();
        return color;
    }

    public static int getThemeColorPrimaryDark(Context ctx) {
        TypedValue typedValue = new TypedValue();
        ctx.getTheme().resolveAttribute(android.R.attr.theme, typedValue, true);
        int[] attribute = new int[]{android.R.attr.colorPrimaryDark};
        TypedArray array = ctx.obtainStyledAttributes(typedValue.resourceId, attribute);
        int color = array.getColor(0, -1);
        array.recycle();
        return color;
    }

    public static int getThemeColorAccent(Context ctx) {
        TypedValue typedValue = new TypedValue();
        ctx.getTheme().resolveAttribute(android.R.attr.theme, typedValue, true);
        int[] attribute = new int[]{android.R.attr.colorAccent};
        TypedArray array = ctx.obtainStyledAttributes(typedValue.resourceId, attribute);
        int color = array.getColor(0, -1);
        array.recycle();
        return color;
    }
}
