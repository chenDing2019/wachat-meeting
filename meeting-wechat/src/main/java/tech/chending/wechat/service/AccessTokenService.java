package tech.chending.wechat.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import tech.chending.wechat.util.HttpReqUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author ChenDing
 */
@Component
public class AccessTokenService {



    private final static String APP_ID = "wx9fb0d9d619eb6248";
    private final static String APP_SECRET = "2df48b3df720e363bc6460cd31e9649b";
    private final static String URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APP_ID+"&secret=" + APP_SECRET;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public  String getAccessToken() throws IOException {

        //首先判断redis中是否存在
        String token = redisTemplate.opsForValue().get("weChat:" + APP_ID);
        if (token == null) {
            //发送请求
            String msg = HttpReqUtil.getRespMsg(URL,"GET",null);
            JSONObject jsonObject = JSONObject.parseObject(msg);
            token = jsonObject.getString("access_token");
            //存入redis中
            redisTemplate.opsForValue().set("weChat:" + APP_ID, token, 1, TimeUnit.HOURS);
            return token;
        }
        return token;
    }
}
