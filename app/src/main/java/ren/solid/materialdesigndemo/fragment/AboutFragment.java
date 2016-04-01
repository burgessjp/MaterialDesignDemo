package ren.solid.materialdesigndemo.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import ren.solid.materialdesigndemo.R;
import ren.solid.materialdesigndemo.fragment.base.BaseFragment;
import ren.solid.materialdesigndemo.fragment.base.WebViewFragment;

/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:20:03
 */
public class AboutFragment extends WebViewFragment {
    private ProgressBar mProgressBar;

    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_blog, container, false);
    }

    @Override
    protected void initView() {
        mProgressBar = (ProgressBar) getContentView().findViewById(R.id.progressbar);
        mWebView = (WebView) getContentView().findViewById(R.id.webView);
        mWebView.loadUrl("file:///android_asset/about.htm");
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setWebChromeClient(new MyWebChromeClient());
        mProgressBar.setMax(100);
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
