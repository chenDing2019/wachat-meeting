package tech.chending.turing.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tech.chending.turing.util.GetRespMsg;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ChenDing
 */

@Component
public class ApiKeyTask {


    @Scheduled(cron = "0 0 0 * * ?")
    public void apiKeyFlushTask() {
        GetRespMsg.apiKeyIndex =  new AtomicInteger(0);;
    }
}
