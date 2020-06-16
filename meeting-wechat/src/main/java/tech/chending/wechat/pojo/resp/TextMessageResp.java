package tech.chending.wechat.pojo.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文本消息
 * @author ChenDing
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TextMessageResp extends BaseMessageResp {
	// 回复的消息内容
	private String content;

}
