package ren.solid.library.utils;

import android.content.Context;
import android.util.Log;

/**
 * Created by _SOLID
 * Date:2016/5/10
 * Time:10:15
 */
public class LogUtils {

    /**
     * 是否为开发者模式(开发模式打印LOG,非开发模式不打印LOG)
     */
    private static boolean mDebug = true;

    private LogUtils() {
    }

    /**
     * 打印info级别的log
     *
     * @param msg
     */
    public static void i(Context context, String msg) {
        if (mDebug) {
            Log.i("*** CurrentPage：" + context.getClass().getSimpleName() + "  Log", msg);
        }
    }

    /**
     * 打印info级别的log
     *
     * @param msg
     */
    public static void i(String msg) {
        if (mDebug) {
            Log.i("Log-----info", msg);
        }
    }

    /**
     * 打印error级别的log
     *
     * @param msg
     */
    public static void e(Context context, String msg) {
        if (mDebug) {
            Log.e("*** CurrentPage：" + context.getClass().getSimpleName() + "  Log", msg);
        }
    }

    /**
     * 打印error级别的log
     *
     * @param msg
     */
    public static void e(String msg) {
        if (mDebug) {
            Log.e("Log-----error", msg);
        }
    }
}
