package cn.carhouse.web.utils;

import cn.carhouse.web.ProgressWebView;
import cn.carhouse.web.event.IEvent;
import cn.carhouse.web.event.JSEvent;

public class WebConfig {
    public static final String PATH = "/web/activity/web";
    public static final int EXTRAS = 0x102030;

    private JSEvent mEvent;
    /**
     * JS注入的名称，和H5约定的
     */
    private String mJSName = "WebConfig";

    private static WebConfig mInstance = new WebConfig();

    public static WebConfig getInstance() {
        return mInstance;
    }

    /**
     * 设置自定义的进度条
     */
    public void setProgressDrawable(int resId) {
        ProgressWebView.mProgressDrawable = resId;
    }

    /**
     * 设置自定义的进度条高度
     */
    public void setProgressHeight(int height) {
        ProgressWebView.mProgressHeight = height;
    }


    public IEvent getEvent() {
        return mEvent;
    }

    /**
     * 设置JS注入
     */
    public void setEvent(JSEvent event) {
        this.mEvent = event;
    }

    /**
     * 设置JS注入名称
     */
    public void setJSName(String name) {
        this.mJSName = name;
    }

    public String getJSName() {
        return mJSName;
    }
}
