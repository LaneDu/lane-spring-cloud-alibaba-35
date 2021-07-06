package com.galaxy.controller;

import com.galaxy.pojo.Resume;
import com.galaxy.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author lane
 * @date 2021年06月22日 下午4:50
 */
@RestController
@RequestMapping("/resume")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;
    //resume/openstate/1545132
    @Value("${server.port}")
    Integer port;

    @GetMapping("/openstate1/{userId}")
    public Integer findDefaultResumeState1(@PathVariable Long userId){
        Resume resume = resumeService.findDefaultResumeByUserId(userId);
//        System.out.println(resume);
        return resume.getIsOpenResume() ;
    }
    @GetMapping("/openstate/{userId}")
    public Integer findDefaultResumeState(@PathVariable Long userId) throws InterruptedException {
        Resume resume = resumeService.findDefaultResumeByUserId(userId);
//        System.out.println(resume);
        //测试熔断Hystrix，阻塞10s
//        TimeUnit.SECONDS.sleep(10);
        return port;
    }

}
