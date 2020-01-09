package cn.carhouse.web.event;

import android.webkit.JavascriptInterface;

import cn.carhouse.base.utils.TSUtils;

/**
 * Web JS交互的事件处理
 * 自己继承这个类去实现，然后调用WebConfig设置
 */
public class JSEvent implements IEvent {
    @Override
    @JavascriptInterface
    public String execute(String params) {
        return null;
    }

    // TODO 图片点击事件，子类复写这个方法
    @JavascriptInterface
    public void openImage(String imageUrl) {
    }
}
