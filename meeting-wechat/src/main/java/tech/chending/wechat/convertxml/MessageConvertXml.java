package tech.chending.wechat.convertxml;

import java.util.Map;

/**
 * @author ChenDing
 */
public interface MessageConvertXml {


    String DEFAULT_MESSAGE = "请求处理异常，请稍候尝试!";
    /**
     * 消息转换成xml
     */
    String messageConvertXml(Map<String,String> map);
}
