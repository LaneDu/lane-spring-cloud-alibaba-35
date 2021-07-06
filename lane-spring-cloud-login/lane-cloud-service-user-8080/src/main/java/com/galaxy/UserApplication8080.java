package com.galaxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author lane
 * @date 2021年06月26日 下午3:10
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients //开启feign的功能
public class UserApplication8080 {
    public static void main(String[] args) {

        SpringApplication.run(UserApplication8080.class,args);
    }
}
