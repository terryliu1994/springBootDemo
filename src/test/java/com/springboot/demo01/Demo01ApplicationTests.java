package com.springboot.demo01;

import com.springboot.demo01.config.dto.Person;
import com.springboot.demo01.sys.dto.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo01ApplicationTests {


    @Autowired
    private Person person;

    @Test
    public void contextLoads() {
        System.out.println(person);
    }

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisCacheManager userCacheManager;

    @Test
    public void testRedisTemplate() {
        User u = new User();
        u.setUserId(10001L);
        u.setUserName("terry");
        u.setPassword("123456");
        redisTemplate.opsForValue().set(u.getUserId().toString(), u);

        User user = (User) redisTemplate.opsForValue().get(u.getUserId().toString());
        System.out.println(user);
    }

    @Test
    public void testRedisCacheManager() {
        User u = new User();
        u.setUserId(10002L);
        u.setUserName("ryunn");
        u.setPassword("123456");
        Cache userCache = userCacheManager.getCache("user");
        userCache.put(u.getUserId().toString(), u);

        User user = (User) userCache.get(u.getUserId().toString(),User.class);
        System.out.println(user);
    }

}
