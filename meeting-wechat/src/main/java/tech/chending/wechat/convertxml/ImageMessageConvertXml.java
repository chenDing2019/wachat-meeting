package tech.chending.wechat.convertxml;

import org.springframework.stereotype.Component;
import tech.chending.turing.constant.ReqType;
import tech.chending.turing.util.GetRespMsg;
import tech.chending.wechat.constant.MessageType;
import tech.chending.wechat.util.MessageUtil;

import java.util.Map;

/**
 * @author ChenDing
 */
@Component(value = MessageType.IMAGE)
public class ImageMessageConvertXml implements MessageConvertXml {

    @Override
    public String messageConvertXml(Map<String, String> map) {
        // 默认返回的文本消息内容
        String respContent = DEFAULT_MESSAGE;
        // 发送方帐号（open_id） 下面三行代码是： 从HashMap中取出消息中的字段；
        String fromUserName = map.get("FromUserName");
        // 公众帐号
        String toUserName = map.get("ToUserName");
        // 接收用户发送的文本消息内容
        String picUrl = map.get("PicUrl");

        //TODO 调用图灵机器人智能回复

        respContent = GetRespMsg.getTextMsg(ReqType.IMAGE, "",picUrl);
        // 回复文本消息 组装要返回的文本消息对象;
        return MessageUtil.getTextMsgXml(fromUserName, toUserName, respContent);
    }
}
