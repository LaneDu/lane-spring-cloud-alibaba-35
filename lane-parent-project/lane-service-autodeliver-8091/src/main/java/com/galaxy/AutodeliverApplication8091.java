package com.galaxy;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author lane
 * @date 2021年06月22日 下午6:21
 */
@SpringBootApplication
@EnableDiscoveryClient
//@EnableEurekaClient
//@EnableHystrix //hystrix的注解
@EnableCircuitBreaker//通用熔断器的注解
//@SpringCloudApplication //上面3个注解的集合
public class AutodeliverApplication8091 {

    public static void main(String[] args) {
        SpringApplication.run(AutodeliverApplication8091.class, args);

    }
    //ribbon负载均衡
    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate2(){
        return new RestTemplate();
    }


    /**
     * 在被监控的微服务中注册一个serlvet，后期我们就是通过访问这个servlet来获取该服务的Hystrix监控数据的
     * 前提：被监控的微服务需要引入springboot的actuator功能
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet2(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
