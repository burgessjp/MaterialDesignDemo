package ren.solid.materialdesigndemo.fragment.base;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import ren.solid.materialdesigndemo.R;

/**
 * Created by _SOLID
 * Date:2016/3/31
 * Time:14:27
 */
public abstract class WebViewFragment extends BaseFragment {

    protected WebView mWebView;
    protected ProgressBar mProgressBar;

    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_webview, container, false);
    }

    /**
     * 需要加载的Url<br/>
     * Example：file:///android_asset/about.htm<br/>
     * http://www.jianshu.com/users/6725c8e8194f/<br/>
     *
     * @return
     */
    protected abstract String getLoadUrl();

    @Override
    protected void initView() {
        mProgressBar = (ProgressBar) getContentView().findViewById(R.id.progressbar);
        mWebView = (WebView) getContentView().findViewById(R.id.webView);
        mWebView.loadUrl(getLoadUrl());
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setWebChromeClient(new MyWebChromeClient());
        mProgressBar.setMax(100);
    }

    public boolean canGoBack() {
        return mWebView != null && mWebView.canGoBack();
    }

    public void goBack() {
        if (mWebView != null) {
            mWebView.goBack();
        }
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            Log.e("onProgressChanged", newProgress + "");
            mProgressBar.setProgress(newProgress);
            if (newProgress == 100) {
                mProgressBar.setVisibility(View.GONE);
            } else {
                mProgressBar.setVisibility(View.VISIBLE);
            }
        }
    }
}
