package com.galaxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lane
 * @date 2021年06月22日 下午5:23
 */
@SpringBootApplication
@EntityScan("com.galaxy.pojo")
// 开启Eureka Client（Eureka独有）
//@EnableEurekaClient
// 开启注册中心客户端 （通 用型注解，比如注册到Eureka、Nacos等）
// 说明：从SpringCloud的Edgware版本开始，不加注解也ok，但是建议大家加上
@EnableDiscoveryClient
public class ResumeApplication8083 {
    public static void main(String[] args) {
        SpringApplication.run(ResumeApplication8083.class, args);
    }
}
