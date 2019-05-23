package com.winning.devops.oauth2.server;

import com.winning.devops.oauth2.server.entity.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.oauth2.server
 * @date: 2019-05-23 22:44
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class LettuceRedisClientTest {

    /**logger*/
    private static final Logger logger = LoggerFactory.getLogger(LettuceRedisClientTest.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    @Test
    public void get() {
        // TODO 测试线程安全
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 1000).forEach(i ->
                executorService.execute(() -> stringRedisTemplate.opsForValue().increment("kk", 1))
        );
        stringRedisTemplate.opsForValue().set("k1", "v1");
        final String k1 = stringRedisTemplate.opsForValue().get("k1");
        logger.info("[字符缓存结果] - [{}]", k1);
        // TODO 以下只演示整合，具体Redis命令可以参考官方文档，Spring Data Redis 只是改了个名字而已，Redis支持的命令它都支持
        String key = "battcn:user:1";
        User user = new User();
        user.setId(1L);
        user.setPassword("pa");
        user.setUsername("u1");
        redisCacheTemplate.opsForValue().set(key, user);
        // TODO 对应 String（字符串）
        final User user1 = (User) redisCacheTemplate.opsForValue().get(key);
        logger.info("[对象缓存结果] - [{}]", user1);
    }

    public static void main(String[] args){
        String finalPassword = "{bcrypt}"+new BCryptPasswordEncoder().encode("123456");
        System.out.println(finalPassword);
        // {bcrypt}$2a$10$wfLGGFN/vwqMHmSmdCiL6OLX9k.yhuK/ykiNynn.o5YrvyXR860Mq
    }
}
