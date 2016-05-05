package ren.solid.library.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by _SOLID
 * Date:2016/4/20
 * Time:16:23
 */
public class NetworkUtils {

    /**
     * 判断网络是否正常连接
     *
     * @param context
     * @return boolean true：网络连接正常 false：网络连接不正常
     */
    public static boolean isNetworkConnected(Context context) {
        if(null == context){
            return false;
        }
        // 获取手机所有连接管理对象（包括wifi，net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            // 获取网络连接管理的对象
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 判断当前网络是否已经连接
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }
}
