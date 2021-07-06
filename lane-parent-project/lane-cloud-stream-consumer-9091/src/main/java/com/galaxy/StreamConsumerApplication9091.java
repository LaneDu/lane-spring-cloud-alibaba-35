package com.galaxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lane
 * @date 2021年06月25日 下午4:45
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StreamConsumerApplication9091 {
    public static void main(String[] args) {
        SpringApplication.run(StreamConsumerApplication9091.class,args);
    }

}
