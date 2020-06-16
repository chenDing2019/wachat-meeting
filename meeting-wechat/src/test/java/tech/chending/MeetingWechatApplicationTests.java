package tech.chending;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.chending.wechat.service.AccessTokenService;
import tech.chending.wechat.util.MenuUtil;

import java.io.IOException;

@SpringBootTest
class MeetingWechatApplicationTests {


    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private MenuUtil menuUtil;
    @Test
    void contextLoads() throws IOException {

        menuUtil.createMenu();
    }

}
