package com.galaxy.controller;

import com.galaxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author lane
 * @date 2021年06月27日 下午6:18
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 用户注册
     * @author lane
     * @date 2021/6/27 下午6:22
     * @param email
     * @param password
     * @param code
     * @return java.lang.Boolean
     */
    @GetMapping("register/{email}/{password}/{code}")
    public Boolean registerUser(@PathVariable("email") String email,
                                @PathVariable("password") String password,
                                @PathVariable("code") String code){

        Boolean aBoolean = userService.registerUser(email, password, code);
        System.out.println("registerUser:"+aBoolean);
        return aBoolean;

    }
    /**
     * 用户是否已经注册
     * @author lane
     * @date 2021/6/27 下午6:23
     * @param email
     * @return java.lang.Boolean
     */
    @GetMapping("isRegistered/{email}")
    public Boolean isRegistered(@PathVariable("email") String email){

        Boolean aBoolean = userService.isRegister(email);
        System.out.println("isRegistered:"+aBoolean);
        return aBoolean;

    }
    /**
     * 用户登陆
     * @author lane
     * @date 2021/6/27 下午6:26
     * @param email
     * @param password
     * @param response
     * @return java.lang.Boolean
     */
    @GetMapping("login/{email}/{password}")
    @ResponseBody
    public String login(@PathVariable("email") String email,
                         @PathVariable("password") String password, HttpServletResponse response){

        Boolean aBoolean = userService.login(email,password, response);
        if(!aBoolean){
            return "false";
        }

        String token = userService.createToken(email,password);
        Cookie cookie = new Cookie("token", token);
//        cookie.setDomain("www.test.com");
//        cookie.setDomain("localhost:8070");
        cookie.setPath("/");
        cookie.setMaxAge(24*60*60);
        response.addCookie(cookie);

        System.out.println("create token123:"+token);

        return token;

    }
    /**
     * 根据用户token查询邮箱
     * @author lane
     * @date 2021/6/27 下午6:28
     * @param token
     * @return java.lang.String
     */
    @GetMapping("info/{token}")
    public String info(@PathVariable("token") String token){

        String email = userService.queryEmail(token);
        System.out.println("info:"+email);
        return email;

    }
}
