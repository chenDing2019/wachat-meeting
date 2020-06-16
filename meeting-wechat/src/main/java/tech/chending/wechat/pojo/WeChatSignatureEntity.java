package tech.chending.wechat.pojo;

import lombok.Data;

/** 封装的从微信服务器发送过来验证信息的实体对象
 * @author ChenDing
 */
@Data
public class WeChatSignatureEntity {

    private String signature;

    private String timestamp;

    private String nonce;

    private String echostr;

}
