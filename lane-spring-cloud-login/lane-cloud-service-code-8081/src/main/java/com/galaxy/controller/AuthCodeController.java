package com.galaxy.controller;

import com.galaxy.service.AuthCodeServiceApi;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lane
 * @date 2021年06月27日 下午4:22
 */
@RestController
@RequestMapping("/code")
public class AuthCodeController {

    @Autowired
    private AuthCodeServiceApi authCodeService;
    /**
     * 发送验证码
     * @author lane
     * @date 2021/6/27 下午4:27
     * @param email
     * @return java.lang.Boolean
     */
    @GetMapping("/create/{email}")
    public Boolean createCode(@PathVariable("email") String email) throws Exception {
        boolean b = authCodeService.sendCode(email);
        System.out.println("验证码send："+b);
        return b;

    }
    /**
     * 校验验证码
     * @author lane
     * @date 2021/6/27 下午4:28
     * @param email
     * @param code
     * @return int
     */
    @PostMapping("/validate/{email}/{code}")
    public int validateCode(@PathVariable("email") String email,@PathVariable("code") String code){
        int validateCode = authCodeService.validateCode(email, code);
        System.out.println("validateCode："+validateCode);
        return validateCode;
    }

}
