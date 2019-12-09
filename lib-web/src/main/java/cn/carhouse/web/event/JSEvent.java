package cn.carhouse.web.event;

import android.webkit.JavascriptInterface;

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
}
