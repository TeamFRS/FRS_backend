package teamFRS.FoodRoadSook;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//public class RedisConfig {
//
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
//        return lettuceConnectionFactory;
//    }
//    @Bean
//    public StringRedisTemplate redisTemplate() {
//        StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory());
//        // explicitly enable transaction support
//        template.setEnableTransactionSupport(true);
//        return template;
//    }
//
//}
