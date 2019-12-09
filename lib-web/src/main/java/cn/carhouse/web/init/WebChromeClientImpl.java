package cn.carhouse.web.init;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Message;

import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;


/**
 * H5打开相机相册的回调监听
 * 进度条的回调监听
 */

public class WebChromeClientImpl extends WebChromeClient {
    // WebView打开相机相册的请求码
    public static final int FILE_REQUEST_CODE = 0x011;
    /**
     * 进度条的回调监听
     */
    private OnWebChromeListener onWebChromeListener;

    /**
     * 打开相册 本地文件等等
     */
    private ValueCallback<Uri> uploadFile;
    private ValueCallback<Uri[]> uploadFiles;
    private Activity mActivity;

    public WebChromeClientImpl(Activity activity) {
        mActivity = activity;
    }

    /**
     * 进度发生改变
     */
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (onWebChromeListener != null) {
            onWebChromeListener.onProgressChanged(view, newProgress);
        }
    }

    /**
     * 接收到标题
     */
    @Override
    public void onReceivedTitle(WebView view, String title) {
        if (onWebChromeListener != null) {
            onWebChromeListener.onReceivedTitle(view, title);
        }
    }

    // For Android 3.0+
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        handleFileChooser(uploadMsg, null);
    }

    // For Android < 3.0
    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        handleFileChooser(uploadMsg, null);
    }

    // For Android  > 4.1.1
    @Override
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        handleFileChooser(uploadMsg, null);
    }

    // For Android  >= 5.0
    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        handleFileChooser(null, filePathCallback);
        return true;
    }

    /**
     * 打开相册 本地文件等等
     */
    private void handleFileChooser(ValueCallback<Uri> uploadMsg, ValueCallback<Uri[]> filePathCallback) {
        if (mActivity == null || mActivity.isFinishing()) {
            return;
        }
        uploadFile = uploadMsg;
        uploadFiles = filePathCallback;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        mActivity.startActivityForResult(Intent.createChooser(intent, "请选择"), FILE_REQUEST_CODE);
    }

    /**
     * Activity回调处理
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 处理相机相册选择
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case FILE_REQUEST_CODE:
                    if (null != uploadFile) {
                        Uri result = data == null || resultCode != Activity.RESULT_OK ? null : data.getData();
                        uploadFile.onReceiveValue(result);
                        uploadFile = null;
                    }
                    if (null != uploadFiles) {
                        Uri result = data == null || resultCode != Activity.RESULT_OK ? null : data.getData();
                        uploadFiles.onReceiveValue(new Uri[]{result});
                        uploadFiles = null;
                    }
                    break;
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            if (null != uploadFile) {
                uploadFile.onReceiveValue(null);
                uploadFile = null;
            }
            if (uploadFiles != null) {
                uploadFiles.onReceiveValue(null);
                uploadFiles = null;
            }
        }
    }



    // 页面标题、加载进度回调监听接口
    public interface OnWebChromeListener {
        void onReceivedTitle(WebView view, String title);

        void onProgressChanged(WebView view, int newProgress);
    }

    public void setOnWebChromeListener(OnWebChromeListener onWebChromeListener) {
        this.onWebChromeListener = onWebChromeListener;
    }

}
