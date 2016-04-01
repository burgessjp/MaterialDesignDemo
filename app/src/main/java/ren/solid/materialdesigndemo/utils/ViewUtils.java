package ren.solid.materialdesigndemo.utils;

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

    /**
     * 根据名字创建Fragment
     *
     * @param fName
     * @return
     */
    public static BaseFragment createFrgment(String fName) {
        BaseFragment resultFragment = null;
        if (fragmentList.containsKey(fName)) {
            resultFragment = fragmentList.get(fName);
        } else {
            if (fName.equals("BlogFragment")) {
                resultFragment = new BlogFragment();
            } else if (fName.equals("MainFragment")) {
                resultFragment = new MainFragment();
            } else if (fName.equals("BookFragment")) {
                resultFragment = new BookFragment();
            } else if (fName.equals("MovieFragment")) {
                resultFragment = new MovieFragment();
            } else if (fName.equals("AboutFragment")) {
                resultFragment = new AboutFragment();
            }

            fragmentList.put(fName, resultFragment);
        }

        return resultFragment;
    }
}
