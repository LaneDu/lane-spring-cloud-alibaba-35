package com.galaxy.controller;

import com.galaxy.service.SendEmailServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lane
 * @date 2021年06月27日 上午11:16
 */
@RestController
@RequestMapping("/email")
@RefreshScope
public class SendEmailController {

    /**
     * 邮件发送器
     */
    @Autowired
    private SendEmailServiceApi sendEmailServiceApi;

    /** 发送验证码信息
     * @author lane
     * @date 2021/6/27 上午11:30
     * @param email
     * @param code
     * @return java.lang.String
     */
    @PostMapping("/{email}/{code}")
    public String sendEmail(@PathVariable("email") String email,@PathVariable("code") String code) throws Exception {
        String msg= sendEmailServiceApi.sendMail(email,"验证码", code);
        return msg;
    }





//    /** 发送验证码信息
//     * @author lane
//     * @date 2021/6/27 上午11:30
//     * @param email
//     * @param code
//     * @return java.lang.String
//     */
//    @PostMapping("/{email}/{code}/simple")
//    public Boolean sendEmailSimple(@PathVariable("email") String email,@PathVariable("code") String code) throws Exception {
//        Boolean msg= sendEmailService.sendMailSimple(email, code);
//        return msg;
//    }
}
