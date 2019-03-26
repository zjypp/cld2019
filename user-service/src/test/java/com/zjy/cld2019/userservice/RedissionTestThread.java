package com.zjy.cld2019.userservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Jason Zhang
 * @Date: 2019/3/26 9:15 AM
 * @Version: 1.0
 * @Des：
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServiceApplicationTests.class)
public class RedissionTestThread implements Runnable {
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    @Override
    public void run() {

        if(redisTemplate == null){
            System.out.println("*********** redissio is null");
            return;
        }

        int stock = Integer.parseInt(redisTemplate.opsForValue().get("aaa").toString());

        if(stock > 0){
            redisTemplate.opsForValue().set("aaa",(stock-1)+"");
            System.out.println( Thread.currentThread().getName() + " lockkey:  "+(stock-1)+"");
        }
    }
}
