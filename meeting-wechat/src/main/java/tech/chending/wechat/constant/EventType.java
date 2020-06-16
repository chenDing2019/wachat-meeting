package tech.chending.wechat.constant;

/**
 * @author ChenDing
 */
public interface EventType {

    /**
     * 事件类型：subscribe(订阅)
     */
    String SUBSCRIBE = "subscribe";

    /**
     * 事件类型：unsubscribe(取消订阅)
     */
    String UNSUBSCRIBE = "unsubscribe";

    /**
     * 事件类型：CLICK(自定义菜单点击事件)
     */
    String CLICK = "click";

    /**
     * 地理位置事件
     */
    String LOCATION = "location";

    /**
     * 扫描事件
     */
    String SCAN = "scan";

    /**
     * 视图事件
     */
    String VIEW = "view";
}
