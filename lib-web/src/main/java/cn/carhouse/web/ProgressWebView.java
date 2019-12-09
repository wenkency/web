package cn.carhouse.web;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.tencent.smtt.sdk.WebView;


/**
 * ================================================================
 * 版权: 爱车小屋所有（C） 2017
 * <p>
 * 作者：刘付文 （61128910@qq.com）
 * <p>
 * 时间: 2017-02-13 16:05
 * <p>
 * 描述：带进度条的WebView和一些初始化操作
 * ================================================================
 */
public class ProgressWebView extends LinearLayout {
    public static int mProgressDrawable = R.drawable.progress_bar_states;
    public static int mProgressHeight = 2;
    private ProgressBar progressbar;
    private WebView mWebView;

    public ProgressWebView(Context context) {
        this(context, null);
    }

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e("ProgressWebView", "init");
        addProgressbar(context);// 添加进度条
    }

    /**
     * 添加进度条
     */
    private void addProgressbar(Context context) {
        removeAllViews();
        setOrientation(VERTICAL);
        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.FILL_PARENT, dip2px(mProgressHeight)));
        Drawable drawable = context.getResources().getDrawable(mProgressDrawable);
        progressbar.setProgressDrawable(drawable);
        addView(progressbar);
        // 暂不优化，优化弹窗打不开
        mWebView = new WebView(context);
        mWebView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

    }

    public int dip2px(int dip) {
        // 缩放比例(密度)
        float density = getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5);
    }

    @Override
    protected void onAttachedToWindow() {
        addView(mWebView);
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        removeView(mWebView);
        super.onDetachedFromWindow();
    }

    public WebView getWebView() {
        return mWebView;
    }


    public ProgressBar getProgressbar() {
        return progressbar;
    }
}