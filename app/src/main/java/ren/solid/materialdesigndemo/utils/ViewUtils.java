package ren.solid.materialdesigndemo.utils;

import android.content.Context;
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

    /**
     * 将dp转换成对应的像素值
     *
     * @param context
     * @param dp
     * @return
     */
    public static float convertDp2Px(Context context, int dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
    }

    /**
     * 将sp转换成对应的像素值
     *
     * @param context
     * @param sp
     * @return
     */
    public static float convertSp2Px(Context context, int sp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, metrics);
    }
}
