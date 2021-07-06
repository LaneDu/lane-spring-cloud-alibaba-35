package com.galaxy.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lane
 * @date 2021年06月24日 下午4:41
 */
// 原来：http://lagou-service-resume/resume/openstate/ + userId;
// @FeignClient表明当前类是一个Feign客户端，value指定该客户端要请求的服务名称（登记到注册中心上的服务提供者的服务名称）
@FeignClient(value = "lane-service-resume",fallback = ResumeFallback.class,path = "/resume") //指定降级走的方法
//@RequestMapping("/resume") //因为降级的时候必须放在上面才行，这里注释下
public interface ResumeServiceFeignClient {


    // Feign要做的事情就是，拼装url发起请求
    // 我们调用该方法就是调用本地接口方法，那么实际上做的是远程请求
    @GetMapping("/openstate/{userId}")
    public Integer findDefaultResumeState(@PathVariable("userId") Long userId);

}