package tech.chending.wechat.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.chending.wechat.service.AccessTokenService;

import java.io.IOException;

/**
 * @author ChenDing
 */
@Component
public class MenuUtil {

    @Autowired
    private AccessTokenService accessTokenService;

    public void createMenu() throws IOException {
        String accessToken = accessTokenService.getAccessToken();

        String menuUrl =  " https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accessToken;

        String data = "{\n" +
                "    \"button\": [\n" +
                "        {\n" +
                "            \"name\": \"会议\",\n" +
                "            \"sub_button\": [\n" +
                "                {\n" +
                "                    \"type\":\"view\",\n" +
                "                    \"name\":\"会议评价\",\n" +
                "                    \"url\":\"http://www.baidu.com/\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\":\"view\",\n" +
                "                    \"name\":\"会议管理\",\n" +
                "                    \"url\":\"http://www.baidu.com/\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"公告板\",\n" +
                "            \"sub_button\": [\n" +
                "                {\n" +
                "                    \"type\":\"click\",\n" +
                "                    \"name\":\"每日签到\",\n" +
                "                    \"key\":\"V1001_TODAY_MUSIC\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\":\"view\",\n" +
                "                    \"name\":\"排行榜\",\n" +
                "                    \"url\":\"http://www.baidu.com/\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}\n";
        String post = HttpReqUtil.getRespMsg(menuUrl, "POST",data);
        System.out.println(post);
    }
}
