package tech.chending.wechat.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import tech.chending.wechat.convertxml.MessageConvertXml;
import tech.chending.wechat.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

/**
 * @author ChenDing
 */
@Service
public class MessageService implements ApplicationContextAware {

    //获取ioc 容器
    private ApplicationContext applicationContext;

    /**
     * 处理微信发来的请求
     *
     * @param request
     * @return string
     */
    public String processRequestMessage(HttpServletRequest request) {
        String respMessage = null;
        try {
            // xml请求解析 调用消息工具类MessageUtil解析微信发来的xml格式的消息，解析的结果放在HashMap里；
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            // 消息类型
            String msgType = requestMap.get("MsgType");
            //根据消息类型,从ioc容器中获取对应的消息处理对象
            MessageConvertXml convertXml = applicationContext.getBean(msgType, MessageConvertXml.class);
            respMessage = convertXml.messageConvertXml(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respMessage;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
