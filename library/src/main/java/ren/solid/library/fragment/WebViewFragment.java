package ren.solid.library.fragment;

import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import ren.solid.library.R;
import ren.solid.library.fragment.base.BaseFragment;


/**
 * Created by _SOLID
 * Date:2016/3/31
 * Time:14:27
 */
public abstract class WebViewFragment extends BaseFragment {

    protected WebView mWebView;
    protected ProgressBar mProgressBar;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_webview;
    }

    /**
     * 需要加载的Url<br/>
     * assert中的文件：file:///android_asset/about.htm<br/>
     * 网页： http://www.jianshu.com/users/6725c8e8194f/<br/>
     * <p/>
     *
     * @return
     */
    protected abstract String getLoadUrl();

    @Override
    protected void setUpView() {
        mProgressBar = (ProgressBar) getContentView().findViewById(R.id.progressbar);
        mWebView = (WebView) getContentView().findViewById(R.id.webView);

        initWebViewSettings();
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setWebChromeClient(new MyWebChromeClient());

        mProgressBar.setMax(100);
        mWebView.loadUrl(getLoadUrl());

    }

    private void initWebViewSettings() {
        WebSettings webSettings = mWebView.getSettings();

        //支持获取手势焦点，输入用户名、密码或其他
        mWebView.requestFocusFromTouch();

        webSettings.setJavaScriptEnabled(true);  //支持js

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true);  //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小


        webSettings.setSupportZoom(true);  //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。
        //若上面是false，则该WebView不可缩放，这个不管设置什么都不能缩放。

        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
        webSettings.supportMultipleWindows();  //多窗口
        // webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //关闭webview中缓存
        webSettings.setAllowFileAccess(true);  //设置可以访问文件
        webSettings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true);  //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
    }

    public boolean canGoBack() {
        return mWebView != null && mWebView.canGoBack();
    }

    public void goBack() {
        if (mWebView != null) {
            mWebView.goBack();
        }
    }

    //WebViewClient就是帮助WebView处理各种通知、请求事件的。
    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

//        shouldOverrideUrlLoading(WebView view, String url)  最常用的，比如上面的。
//        //在网页上的所有加载都经过这个方法,这个函数我们可以做很多操作。
//        //比如获取url，查看url.contains(“add”)，进行添加操作
//
//        shouldOverrideKeyEvent(WebView view, KeyEvent event)
//        //重写此方法才能够处理在浏览器中的按键事件。
//
//        onPageStarted(WebView view, String url, Bitmap favicon)
//        //这个事件就是开始载入页面调用的，我们可以设定一个loading的页面，告诉用户程序在等待网络响应。
//
//        onPageFinished(WebView view, String url)
//        //在页面加载结束时调用。同样道理，我们可以关闭loading 条，切换程序动作。
//
//        onLoadResource(WebView view, String url)
//        // 在加载页面资源时会调用，每一个资源（比如图片）的加载都会调用一次。
//
//        onReceivedError(WebView view, int errorCode, String description, String failingUrl)
//        // (报告错误信息)
//
//        doUpdateVisitedHistory(WebView view, String url, boolean isReload)
//        //(更新历史记录)
//
//        onFormResubmission(WebView view, Message dontResend, Message resend)
//        //(应用程序重新请求网页数据)
//
//        onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host,String realm)
//        //（获取返回信息授权请求）
//
//        onReceivedSslError(WebView view, SslErrorHandler handler, SslError error)
//        //重写此方法可以让webview处理https请求。
//
//        onScaleChanged(WebView view, float oldScale, float newScale)
//        // (WebView发生改变时调用)
//
//        onUnhandledKeyEvent(WebView view, KeyEvent event)
//        //（Key事件未被加载时调用）
    }

    //WebChromeClient是辅助WebView处理Javascript的对话框，网站图标，网站title，加载进度等
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
//        //获取Web页中的title用来设置自己界面中的title
//        //当加载出错的时候，比如无网络，这时onReceiveTitle中获取的标题为 找不到该网页,
//        //因此建议当触发onReceiveError时，不要使用获取到的title
//        @Override
//        public void onReceivedTitle(WebView view, String title) {
//            MainActivity.this.setTitle(title);
//        }
//
//        @Override
//        public void onReceivedIcon(WebView view, Bitmap icon) {
//            //
//        }
//
//        @Override
//        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
//            //
//            return true;
//        }
//
//        @Override
//        public void onCloseWindow(WebView window) {
//        }
//
//        //处理alert弹出框，html 弹框的一种方式
//        @Override
//        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
//            //
//            return true;
//        }
//
//        //处理confirm弹出框
//        @Override
//        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult
//                result) {
//            //
//            return true;
//        }
//
//        //处理prompt弹出框
//        @Override
//        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
//            //
//            return true;
//        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null)
            mWebView.onPause();
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null)
            mWebView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null)
            mWebView.destroy();
    }


}
