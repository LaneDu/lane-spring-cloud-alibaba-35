package com.galaxy.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author lane
 * @date 2021年06月22日 下午6:18
 */
@RestController
@RequestMapping("/autodeliver")
public class AutodeliverController {
    @Autowired
    RestTemplate restTemplate;


    @GetMapping("/checkState1/{userId}")
    public Integer findResumeOpenState1(@PathVariable Long userId){

        return  restTemplate.getForObject("http://localhost:8080/resume/openstate/"+userId, Integer.class);

    }

    @Autowired
    private DiscoveryClient discoveryClient;
    /**
     * 服务注册到Eureka之后的改造
     * @author lane
     * @date 2021/6/23 下午2:52
     * @param userId
     * @return java.lang.Integer
     */
    @GetMapping("/checkState2/{userId}")
    public Integer findResumeOpenState2(@PathVariable Long userId){
        // 1、从 Eureka Server中获取lane-service-resume服务的实例信息（使用客户端对象做这件事）
        List<ServiceInstance> instances = discoveryClient.getInstances("lane-service-resume");
        // 2、如果有多个实例，选择一个使用(负载均衡的过程)
        ServiceInstance instanceInfo = instances.get(0);
        int port = instanceInfo.getPort();
        // 3、从元数据信息获取host port
        String host = instanceInfo.getHost();

        String url = "http://"+host+":"+port+"/resume/openstate/"+userId;
        System.out.println("===============>>>从EurekaServer集群获取服务实例拼接的url：" + url);
        // 调用远程服务—> 简历微服务接口  RestTemplate  -> JdbcTempate
        // httpclient封装好多内容进行远程调用
        return  restTemplate.getForObject(url, Integer.class);

    }


    /**
     * 使用Ribbon负载均衡
     * @param userId
     * @return
     */
    @GetMapping("/checkState/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId) {
        // 使用ribbon不需要我们自己获取服务实例然后选择一个那么去访问了（自己的负载均衡）
        String url = "http://lane-service-resume/resume/openstate/" + userId;  // 指定服务名
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }
    /**
     * 提供者模拟处理超时，调用方法添加Hystrix控制
     * @param userId
     * @return
     */
    @HystrixCommand(
            // 线程池标识，要保持唯一，不唯一的话就共用了
            threadPoolKey = "findResumeOpenStateTimeout",
            // 线程池细节属性配置
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize",value = "1"), // 线程数
                    @HystrixProperty(name="maxQueueSize",value="20") // 等待队列长度
            },
            commandProperties = {
            // 每一个属性都是一个HystrixProperty,2s熔断
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000")
    })
    @GetMapping("/checkStateTimeout/{userId}")
    public Integer findResumeOpenStateTimeout(@PathVariable Long userId) {
        // 使用ribbon不需要我们自己获取服务实例然后选择一个那么去访问了（自己的负载均衡）
        String url = "http://lane-service-resume/resume/openstate/" + userId;  // 指定服务名
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }
    /**
     * 提供者模拟处理超时，调用方法添加Hystrix控制，添加熔断和服务回退
     * @param userId
     * @return
     */
    @HystrixCommand(
            // 线程池标识，要保持唯一，不唯一的话就共用了
            threadPoolKey = "findResumeOpenStateTimeoutFallback",
            // 线程池细节属性配置
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize",value = "2"), // 线程数
                    @HystrixProperty(name="maxQueueSize",value="20") // 等待队列长度
            },
            commandProperties = {
                    // 每一个属性都是一个HystrixProperty,2s熔断
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000"),
                    // hystrix高级配置，定制工作过程细节

                    // 统计时间窗口定义
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds",value = "8000"),
                    // 统计时间窗口内的最小请求数
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "2"),
                    // 统计时间窗口内的错误数量百分比阈值
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
                    // 自我修复时的活动窗口长度
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "3000")



            },fallbackMethod = "fallbackMethod")//回退方法
    @GetMapping("/checkStateTimeoutFallback/{userId}")
    public Integer findResumeOpenStateTimeoutFallback(@PathVariable Long userId) {
        // 使用ribbon不需要我们自己获取服务实例然后选择一个那么去访问了（自己的负载均衡）
        String url = "http://lane-service-resume/resume/openstate/" + userId;  // 指定服务名
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }
    /**
     * 默认返回方法调用，注意参数和返回值要和原方法保持一致
     * @author lane
     * @date 2021/6/24 上午10:37
     * @param userId
     * @return java.lang.Integer
     */
    public Integer fallbackMethod(Long userId){

        return -1;
    }


}
