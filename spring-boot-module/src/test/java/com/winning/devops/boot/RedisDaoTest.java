package com.winning.devops.boot;

import com.winning.devops.boot.redis.RedisDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.boot
 * @date: 2019-04-10 13:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDaoTest {

    @Autowired
    private RedisDao redisDao;

    @Test
    public void testRedis(){
        redisDao.setKey("name","chensj");
        redisDao.setKey("age","20");
        System.out.println(redisDao.getKey("name"));
        System.out.println(redisDao.getKey("age"));
    }
}
