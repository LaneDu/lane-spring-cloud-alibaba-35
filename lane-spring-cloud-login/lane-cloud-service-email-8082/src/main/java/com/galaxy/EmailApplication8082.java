package com.galaxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lane
 * @date 2021年06月27日 上午10:11
 */
@SpringBootApplication
@EnableDiscoveryClient
public class EmailApplication8082 {
    public static void main(String[] args) {

        SpringApplication.run(EmailApplication8082.class,args);
    }
}
