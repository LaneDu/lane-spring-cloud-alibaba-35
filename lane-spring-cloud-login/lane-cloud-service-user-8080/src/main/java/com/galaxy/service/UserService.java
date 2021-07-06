package com.galaxy.service;

import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;

/**
 * @author lane
 * @date 2021年06月27日 下午5:07
 */
public interface UserService {

    Boolean registerUser(String email,String password,String code);
    Boolean isRegister(String email);
    boolean login( String email,String password, HttpServletResponse response);
    String queryEmail(String token);
    String createToken(String email, String password);
}
