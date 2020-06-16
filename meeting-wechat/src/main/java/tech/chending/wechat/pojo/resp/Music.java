package tech.chending.wechat.pojo.resp;

import lombok.Data;

/**
 * 音乐model
 * @author ChenDing
 */
@Data
public class Music {
	// 音乐名称
	private String title;
	// 音乐描述
	private String description;
	// 音乐链接
	private String musicUrl;
	// 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	private String hQMusicUrl;
}

