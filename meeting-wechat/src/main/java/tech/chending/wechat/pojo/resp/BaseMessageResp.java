package tech.chending.wechat.pojo.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息基类（公众帐号 -> 普通用户）
 * @author ChenDing
 */
@Data
public class BaseMessageResp implements Serializable {
	// 接收方帐号（收到的OpenID）
	private String toUserName;
	// 开发者微信号
	private String fromUserName;
	// 消息创建时间 （整型）
	private long createTime;
	// 消息类型（text/music/news）
	private String msgType;
	// 位0x0001被标志时，星标刚收到的消息
	private int funcFlag;
}
