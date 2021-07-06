package com.galaxy.controller;

import com.galaxy.service.ResumeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 该类用于模拟，我们要使用共享的那些配置信息做一些事情
 * @author lane
 * @date 2021年06月25日 上午11:25
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {
    // 和取本地配置信息一样
    @Value("${lagou.message}")
    private String message;
    @Value("${mysql.url}")
    private String url;


    // 内存级别的配置信息
    // 数据库，redis配置信息

    @GetMapping("/viewconfig")
    public String viewconfig() {
        return "lagouMessage==>" + message  + " mysqlUrl=>" + url;
    }

}
