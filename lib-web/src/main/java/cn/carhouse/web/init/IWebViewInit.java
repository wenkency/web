package cn.carhouse.web.init;


import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * WebView初始化接口定义
 */

public interface IWebViewInit {
    /**
     * 1. 初始化和设置WebView
     */
    WebView initWebView(WebView webView);

    /**
     * 2. 初始化WebViewClient
     */
    WebViewClient initWebViewClient();

    /**
     * 3. 初始化WebChromeClient
     */
    WebChromeClient initWebChromeClient();
}
