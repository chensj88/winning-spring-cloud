package com.winning.devops.boot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.boot.redis
 * @date: 2019-04-10 13:54
 */
@Repository
public class RedisDao {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setKey(String key,String value){
        ValueOperations<String, String> ops= stringRedisTemplate.opsForValue();
        //1 分钟过期
        ops.set(key,value , 1 , TimeUnit.MINUTES);
    }

    public String getKey(String key){
        ValueOperations<String, String> ops= stringRedisTemplate.opsForValue();
        return ops.get(key);
    }
}
