package tech.chending.wechat.service;


import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.chending.entity.WachatUser;
import tech.chending.service.WachatUserService;
import tech.chending.wechat.util.HttpReqUtil;

import java.io.IOException;

/**
 * 获取微信用户的信息,并存入数据库中
 *
 * @author ChenDing
 */
@Service
public class UserInfoService {

    @Autowired
    private WachatUserService  wachatUserService;

    @Autowired
    private AccessTokenService accessTokenService;


    /**
     * 收集微信用户信息并存入数据库中
     * @param openid
     */
    public void saveWaChatUser(String openid) {
        //判断数据库中是否有该用户
        boolean flag = wachatUserService.queryByOpenid(openid);
        if (!flag) {
            //没有该用户,存入数据库中
            //获取用户信息
            WachatUser waChatUser = getWaChatUser(openid);
            if (waChatUser == null) {
                return;
            }
            //存入数据库
            wachatUserService.insert(waChatUser);
        }
    }


    private WachatUser getWaChatUser(String openid) {
        try {
            //url请求地址
            String urlTemplate = " https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
            String url = urlTemplate.replace("ACCESS_TOKEN", accessTokenService.getAccessToken())
                    .replace("OPENID", openid);
            //发送请求
            String data = HttpReqUtil.getRespMsg(url, "GET", null);
            System.out.println(data);
            //将结果转换成wachatUser 对象
            JsonMapper jsonMapper = new JsonMapper();
            return jsonMapper.readValue(data, WachatUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
