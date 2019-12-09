package cn.carhouse.web.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.URLUtil;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebView;

import cn.carhouse.web.bean.WebData;


/**
 * WebView初始化和加载页面的工具类
 */

public class WebUtils {
    private static boolean isInit;

    private WebUtils() {
    }

    private static class Holder {
        private static final WebUtils INSTANCE = new WebUtils();
    }

    public static WebUtils getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 初始化
     */
    public void init(Context context) {
        if (context == null) {
            return;
        }
        if (isInit) {
            return;
        }
        //--搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        try {
            QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
                @Override
                public void onViewInitFinished(boolean arg0) {
                }

                @Override
                public void onCoreInitFinished() {
                }
            };
            //x5内核初始化接口
            QbSdk.initX5Environment(context.getApplicationContext(), cb);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        isInit = true;
    }

    private void loadWebPage(WebView webView, String url) {
        if (webView != null) {
            if (url.startsWith("http")) {
                webView.loadUrl(url);
            }

        } else {
            // throw new NullPointerException("WebView is null!");
        }
    }

    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);
    }

    public void loadPage(WebView webView, String url) {
        //如果是电话协议
        if (url.contains("tel:")) {
            callPhone(webView.getContext(), url);
            return;
        }
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView, url);
        }
    }


    private void callPhone(Context context, String uri) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        context.startActivity(intent);
    }

    /**
     * 打开WebActivity
     */
    public void startWebActivity(Context activity, String url) {
        startWebActivity(activity, url, null);
    }

    /**
     * 打开WebActivity
     */
    public void startWebActivity(Context context, String url, String title) {
        // WebView初始化
        WebData data = new WebData();
        data.url = url;
        data.title = title;
        startWebActivity(context, data);
    }

    /**
     * 打开WebActivity
     */
    public void startWebActivity(Context context, WebData data) {
        if (context == null) {
            return;
        }
        init(context);
        ARouter.getInstance().build(WebConfig.PATH)
                .withSerializable("mWebData", data)
                .navigation(context);
    }
}
