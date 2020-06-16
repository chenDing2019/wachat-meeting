package tech.chending.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
/**
 * (WachatUser)实体类
 *
 * @author makejava
 * @since 2020-06-15 18:10:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WachatUser implements Serializable {
    private static final long serialVersionUID = 546291170055780270L;
    /**
    * 主键id
    */
    private Integer id;
    /**
    * 微信名加密过后的字符串，唯一
    */
    private String openid;
    /**
    * 微信昵称
    */
    private String nickname;
    /**
    * 性别（0：男，1：女，2：未知）
    */
    private Integer sex;
    /**
    * 省份
    */
    private String province;
    /**
    * 城市
    */
    private String city;
    /**
    * 国家
    */
    private String country;
    /**
    * 微信头像地址
    */
    private String headimgurl;
    /**
    * 是否关注（0：关注，1：未关注）
    */
    private Integer subscribe;
    /**
    * 语言
    */
    private String language;
    /**
    * 备注
    */
    private String remark;
    /**
    * 状态（0：可用，1：不可用）
    */
    private Integer status;


    //额外字段,用来辅助jackson对象封装
    private Integer subscribe_time;
    private String unionid;
    private int groupid;
    private Integer[] tagid_list;
    private String subscribe_scene;
    private String qr_scene;
    private String qr_scene_str;
}