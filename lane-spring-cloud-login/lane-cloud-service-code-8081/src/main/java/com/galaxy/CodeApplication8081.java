package com.galaxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author lane
 * @date 2021年06月27日 下午3:34
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CodeApplication8081 {
    public static void main(String[] args) {
        SpringApplication.run(CodeApplication8081.class,args);
    }
}
