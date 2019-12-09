package cn.carhouse.web.init;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.View;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * WEbView初始化的定义
 */

public class WebViewInitImpl implements IWebViewInit {

    private static final String USER_AGENT = "appCarB";


    private WebChromeClientImpl mWebChromeClient;
    private Activity mActivity;

    public WebViewInitImpl(Activity activity) {
        mActivity = activity;
    }

    @Override
    public WebView initWebView(WebView webView) {
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        WebSettings webSetting = webView.getSettings();
        webSetting.setUserAgentString(USER_AGENT + webSetting.getUserAgentString());

        webSetting.setDatabaseEnabled(true);
        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setTextSize(WebSettings.TextSize.NORMAL);

        // ===设置JS可用
        webSetting.setJavaScriptEnabled(true);
        // JS打开窗口
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        // ===设置JS可用
        // 可以访问文件
        webSetting.setAllowFileAccess(true);
        // ===缩放可用
        webSetting.setSupportZoom(true);
        webSetting.setDisplayZoomControls(false); //隐藏原生的缩放控件
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS); //设置缩放功能   //能不能缩放 取决于网页设置
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setBuiltInZoomControls(true);
        // 窗口适配
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSetting.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        // ===缩放可用
        // 支持多窗口
        webSetting.setSupportMultipleWindows(true);
        // ===============缓存
        webSetting.setCacheMode(WebSettings.LOAD_DEFAULT);// 决定是否从网络上取数据。
        webSetting.setAppCacheEnabled(true);
        // ===============缓存
        webSetting.setUseWideViewPort(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // ==定位
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        // ==定位
        return webView;
    }

    @Override
    public WebViewClient initWebViewClient() {
        return new WebViewClientImpl();
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        mWebChromeClient = new WebChromeClientImpl(mActivity);
        return mWebChromeClient;
    }

    /**
     * 页面标题、进度回调
     */
    public void setOnWebChromeListener(WebChromeClientImpl.OnWebChromeListener onWebChromeListener) {
        if (mWebChromeClient != null) {
            mWebChromeClient.setOnWebChromeListener(onWebChromeListener);
        }

    }

    /**
     * 选择相机相册处理
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mWebChromeClient != null) {
            mWebChromeClient.onActivityResult(requestCode, resultCode, data);
        }
    }
}