package cn.carhouse.web.event;

import android.webkit.JavascriptInterface;

/**
 * Web交互事件处理的接口定义
 */
public interface IEvent {
    /**
     * H5调用原生方法
     *
     * @param params 传递的是Json数据
     * @return 也是Json数据
     */
    @JavascriptInterface
    String execute(String params);
}
