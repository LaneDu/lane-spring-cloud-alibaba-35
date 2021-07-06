package com.galaxy.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.galaxy.config.SentinelFallBack;
import com.galaxy.service.ResumeServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lane
 * @date 2021年06月24日 下午4:45
 */
@RestController
@RequestMapping("/autodeliver")
public class AutoDeliverController {



    @Autowired
    ResumeServiceFeignClient resumeServiceFeignClient;
    /**

     * @SentinelResource value：定义资源名
     * blockHandlerClass：指定Sentinel规则异常兜底逻辑所在class类
     * blockHandler：指定Sentinel规则异常兜底逻辑具体哪个⽅法
     * fallbackClass：指定Java运⾏时异常兜底逻辑所在class类
     * fallback：指定Java运⾏时异常兜底逻辑具体哪个⽅法

     */
    @GetMapping("/checkState/{userId}")
    @SentinelResource(value = "findResumeOpenState",
            blockHandlerClass = SentinelFallBack.class,
            blockHandler = "handleException",
            fallbackClass = SentinelFallBack.class,
            fallback = "handleError"
    )
    public Integer findResumeOpenState(@PathVariable("userId") Long userId){

        Integer defaultResumeState1 = resumeServiceFeignClient.findDefaultResumeState(userId);
        return defaultResumeState1;
    }


}
