package cn.carhouse.web.init;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * 对加载Url回调统一处理
 * 根据公司业务，自行处理
 */

public class WebViewClientImpl extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        if (webView != null && url != null) {
            Context context = webView.getContext();
            if (url.endsWith(".apk")) {
                Uri apkUri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, apkUri);
                context.startActivity(intent);
            } else if (url.startsWith("http")) {
                webView.loadUrl(url);
            } else {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                if (isInstall(context, intent)) {
                    context.startActivity(intent);
                }
            }
        }
        return true;
    }


    // 判断app是否安装
    private boolean isInstall(Context context, Intent intent) {
        return context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size() > 0;
    }
}
