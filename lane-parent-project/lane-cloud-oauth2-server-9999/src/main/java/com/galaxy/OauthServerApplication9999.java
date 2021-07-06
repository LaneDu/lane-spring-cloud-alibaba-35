package com.galaxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lane
 * @date 2021年06月29日 下午7:18
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OauthServerApplication9999 {
    public static void main(String[] args) {
        SpringApplication.run(OauthServerApplication9999.class,args);
    }
}