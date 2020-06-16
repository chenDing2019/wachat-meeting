package tech.chending.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.chending.wechat.pojo.WeChatSignatureEntity;
import tech.chending.wechat.service.MessageService;
import tech.chending.wechat.util.WeChatSignatureUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ChenDing
 */

@RestController
@RequestMapping("weChat")
public class WeChatSignatureController {

    @Autowired
    private MessageService messageService;

    /**
     * 处理来自微信服务器的请求,验证是否为微信请求
     *
     * @param signature
     * @return
     */
    @GetMapping("signature")
    public String weChatSignature(final WeChatSignatureEntity signature) {

        boolean flag = WeChatSignatureUtil.signature(signature);
        if (flag) {
            return signature.getEchostr();
        }
        return "";
    }

    /**
     * 处理微信服务器发送来的消息
     *
     * @param request
     * @return
     */
    @PostMapping("signature")
    public String weChatMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {

        return messageService.processRequestMessage(request);
    }
}
