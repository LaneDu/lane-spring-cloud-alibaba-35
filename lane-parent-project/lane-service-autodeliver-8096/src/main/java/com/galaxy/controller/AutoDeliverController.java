package com.galaxy.controller;

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

    @GetMapping("/checkState/{userId}")
    public Integer findResumeOpenState(@PathVariable("userId") Long userId){
        System.out.println("访问了autodeliver8096");
        Integer defaultResumeState1 = resumeServiceFeignClient.findDefaultResumeState(userId);
        return defaultResumeState1;
    }
}
