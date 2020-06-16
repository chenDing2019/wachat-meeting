package tech.chending;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ChenDing
 */
@SpringBootApplication
@MapperScan(basePackages = "tech.chending.mapper")
//@EnableScheduling
public class MeetingWechatApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeetingWechatApplication.class, args);
    }

}
