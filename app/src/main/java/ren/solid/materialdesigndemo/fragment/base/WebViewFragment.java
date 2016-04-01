package ren.solid.materialdesigndemo.fragment.base;

import android.webkit.WebView;

/**
 * Created by _SOLID
 * Date:2016/3/31
 * Time:14:27
 */
public abstract class WebViewFragment extends BaseFragment {

    protected WebView mWebView;

    public boolean canGoBack() {
        return mWebView != null && mWebView.canGoBack();
    }

    public void goBack() {
        if (mWebView != null) {
            mWebView.goBack();
        }
    }
}
