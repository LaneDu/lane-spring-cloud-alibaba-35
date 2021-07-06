package com.galaxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author lane
 * @date 2021年06月24日 下午4:35
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients //开启feign的功能
public class AutodeliverApplication8096 {
    public static void main(String[] args) {
        SpringApplication.run(AutodeliverApplication8096.class,args);
    }
}
