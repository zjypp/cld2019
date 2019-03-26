package com.zjy.cld2019.userservice;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    private String lockKey ="redission_lock";

    @Test
    public void contextLoads() {

    }

    @Test
    public void redisTest(){
        //redisTemplate.opsForValue().set("k12","hello world22");
        System.out.println("*** redis ");

        redisTemplate.opsForValue().set("aaa","100");
        Assert.assertEquals("100", redisTemplate.opsForValue().get("aaa"));
        System.out.println("*************");

    }


    /**
     * redission 的测试类,不加Redission锁
     */
    @Test
    public void redissionTest() {


        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
        for (int i=0;i<=10;i++){
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        int stock = Integer.parseInt(redisTemplate.opsForValue().get("aaa").toString());
                    }
                    catch (Exception e){
                        System.out.println("********"+e.getMessage());
                    }

                    /* if(stock > 0){
                        redisTemplate.opsForValue().set("aaa",(stock-1)+"");
                        System.out.println( Thread.currentThread().getName() + " lockkey:  "+(stock-1)+"");
                    }     */
                    System.out.println("*********"+Thread.currentThread().getName());

                }
            });
        }

    }

    /**
     * redission 的测试类
     */
    @Test
    public void redissionLockTest(){
        //执行的业务代码
        for(int i=0; i < 55; i++){
            RLock lock = redissonClient.getLock(lockKey);
            lock.lock(60, TimeUnit.SECONDS); //设置60秒自动释放锁  （默认是30秒自动过期）
            int stock = Integer.parseInt(redisTemplate.opsForValue().get("aaa").toString());
            if(stock > 0){
                redisTemplate.opsForValue().set("aaa",(stock-1)+"");
                System.out.println("test2_:lockkey:"+lockKey+",stock:"+(stock-1)+"");
            }
            lock.unlock(); //释放锁
        }

    }
}

