package tech.chending.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author ChenDing
 */
@Configuration
public class RedisConfig {

    //自定义RedisTemplate使用json序列化器,默认使用的是JdkSerializationRedisSerializer

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        //设置连接工厂
        template.setConnectionFactory(connectionFactory);
        //设置默认的序列化器
        template.setDefaultSerializer(serializer);
        //序列化key
        template.setKeySerializer(serializer);
        //序列化值
        template.setValueSerializer(serializer);
        //序列化hash key
        template.setHashKeySerializer(serializer);
        //序列化hash 的值
        template.setHashValueSerializer(serializer);
        //序列化字符串
        template.setStringSerializer(new StringRedisSerializer());
        return template;
    }
}
