package tech.chending.wechat.convertxml;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import tech.chending.wechat.constant.EventType;
import tech.chending.wechat.constant.MessageType;
import tech.chending.wechat.service.UserInfoService;
import tech.chending.wechat.util.MessageUtil;

import java.util.Map;

/**
 * @author ChenDing
 */
@Component(value = MessageType.EVENT)
@Slf4j
public class EventMessageConvertXml implements MessageConvertXml{

    @Autowired
    private UserInfoService service;

    @Override
    public String messageConvertXml(Map<String, String> map) {

        // 默认返回的文本消息内容
        String respContent = DEFAULT_MESSAGE;
        // 发送方帐号（open_id） 下面三行代码是： 从HashMap中取出消息中的字段；
        String fromUserName = map.get("FromUserName");
        // 公众帐号
        String toUserName = map.get("ToUserName");
        // 接收用户发送的文本消息内容
        String event = map.get("Event");
        //订阅事件
        switch (event) {
            case EventType.SUBSCRIBE:
                //获取微信用户信息，并保存进数据库
                respContent = "欢迎关注微信公众号";
                service.saveWaChatUser(fromUserName);
                break;
            case EventType.UNSUBSCRIBE:
                //取消订阅事件
                log.info(fromUserName + ":取消了订阅~~~~~");
                break;
            case EventType.CLICK:
                //点击事件
                //TODO 触发点击事件需要做的事情,待实现
                break;
            default:
                log.info("事件不存在~~~~");
        }

        // 回复文本消息 组装要返回的文本消息对象；
        return MessageUtil.getTextMsgXml(fromUserName, toUserName, respContent);
    }


}
