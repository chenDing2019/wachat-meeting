package tech.chending.wechat.pojo.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * 文本消息
 * @author ChenDing
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NewsMessageResp extends BaseMessageResp {
	// 图文消息个数，限制为10条以内
	private int articleCount;
	// 多条图文消息信息，默认第一个item为大图
	private List<Article> articles;

}