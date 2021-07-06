package com.galaxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lane
 * @date 2021年06月27日 下午6:33
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication9002 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication9002.class,args);
    }
}
