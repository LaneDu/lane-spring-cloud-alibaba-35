package com.galaxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author lane
 * @date 2021年06月23日 上午10:31
 */
@SpringBootApplication
//声明当前项目是eureka服务端
@EnableEurekaServer
public class EurekaServerApplication8762 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication8762.class,args);
    }
}
