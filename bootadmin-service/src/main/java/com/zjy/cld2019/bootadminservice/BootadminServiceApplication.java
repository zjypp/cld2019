package com.zjy.cld2019.bootadminservice;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableAdminServer
@Configuration
public class BootadminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootadminServiceApplication.class, args);
    }

}
