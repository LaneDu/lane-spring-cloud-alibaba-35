package com.galaxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author lane
 * @date 2021年06月24日 下午3:34
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableTurbine //开启turbine聚合
public class HystrixTurbineApplication {
    public static void main(String[] args) {

        SpringApplication.run(HystrixTurbineApplication.class,args);

    }
}
