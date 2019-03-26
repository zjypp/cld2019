package com.zjy.cld2019.userservice.redisHelper;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;


/**
 * @Author: Jason Zhang
 * @Date: 2019/3/25 7:09 PM
 * @Version: 1.0
 * @Des：Redission的配置类
 */
@Configuration
public class RedissionConfig {

    @Bean
    public RedissonClient redisson() throws IOException {
        // 本例子使用的是yaml格式的配置文件，读取使用Config.fromYAML，如果是Json文件，则使用Config.fromJSON
        Config config = Config.fromYAML(RedissionConfig.class.getClassLoader().getResource("redission.yml"));
        return Redisson.create(config);
    }


}
