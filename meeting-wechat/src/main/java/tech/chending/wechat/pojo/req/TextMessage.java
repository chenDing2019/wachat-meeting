package tech.chending.wechat.pojo.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文本消息
 * @author ChenDing
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TextMessage extends BaseMessage {
	// 消息内容
	private String content;

}