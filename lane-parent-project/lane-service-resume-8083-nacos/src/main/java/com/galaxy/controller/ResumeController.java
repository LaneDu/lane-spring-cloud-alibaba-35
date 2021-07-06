package com.galaxy.controller;

import com.galaxy.pojo.Resume;
import com.galaxy.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lane
 * @date 2021年06月22日 下午4:50
 */
@RestController
@RequestMapping("/resume")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;
    @Value("${server.port}")
    Integer port;
    //resume/openstate/1545132
    @GetMapping("/openstate1/{userId}")
    public Integer findDefaultResumeState1(@PathVariable Long userId){
        Resume resume = resumeService.findDefaultResumeByUserId(userId);
        System.out.println(resume);
        return resume.getIsOpenResume() ;
    }

    //resume/openstate/1545132
    @GetMapping("/openstate/{userId}")
    public Integer findDefaultResumeState(@PathVariable Long userId){
        Resume resume = resumeService.findDefaultResumeByUserId(userId);
//        System.out.println(resume);
        return port ;
    }
}
