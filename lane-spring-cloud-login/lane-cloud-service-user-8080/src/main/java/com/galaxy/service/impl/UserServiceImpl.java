package com.galaxy.service.impl;


import com.galaxy.dao.TokenDao;
import com.galaxy.dao.UserDao;
import com.galaxy.pojo.Token;
import com.galaxy.pojo.User;
import com.galaxy.service.AuthCodeServiceApi;
import com.galaxy.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author lane
 * @date 2021年06月27日 下午5:07
 */
@Service
public class UserServiceImpl implements UserService {

    @Reference
    private AuthCodeServiceApi authCodeServiceApi;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenDao tokenDao;

    @Override
    public Boolean registerUser(String email, String password,String code) {
        // 判断验证码是否有效
        int i = authCodeServiceApi.validateCode(email, code);
        if (i!=0){
            System.out.println(String.format("vailidate code error:%s", email));
            return  false;
        }

        //是否已经注册过了
        if(isRegister(email)){
            System.out.println(String.format("register error %s has already been registered", email));
            return  false;
        }

        boolean b = saveUser(email, password);

        return b;
    }
    /**
     * 是否已经注册
     * @author lane
     * @date 2021/6/27 下午6:09
     * @param email
     * @return java.lang.Boolean
     */
    @Override
    public Boolean isRegister(String email){

        User user = new User();
        user.setEmail(email);

        Example<User> example = Example.of(user);
        try {
            List<User> users = userDao.findAll(example);
            return !users.isEmpty();
        } catch (Exception ex) {
            System.out.println(String.format("Failed to query information of %s", email));
            ex.printStackTrace();
        }

        return true;

    }


    /**
     * 登录接口
     *
     * @param email 用户邮箱
     * @param password 用户密码
     * @param response 登录响应
     * @return 操作结果
     */

    public boolean login( String email,String password, HttpServletResponse response) {
        // 校验用户信息
        if (!validateUser(email, password)) {
            System.out.println(String.format("Failed to check %s", email));
            return false;
        }



        return true;
    }

    /**
     * 校验用户信息
     *
     * @param email 用户邮箱
     * @param password 用户密码
     * @return 校验结果
     */
    private boolean validateUser(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        Example<User> example = Example.of(user);
        try {
            return userDao.exists(example);
        } catch (Exception ex) {
            System.out.println(String.format("Failed to query information of %s", email));
            ex.printStackTrace();
        }

        return false;
    }

    /**
     * 保存用户信息
     * @author lane
     * @date 2021/6/27 下午5:18
     * @param email
     * @param password
     * @return boolean
     */
    private boolean saveUser(String email,String password){

        User user = new User();
        user.setUsername(String.valueOf(UUID.randomUUID().toString().substring(0,6)));
        user.setEmail(email);
        user.setPassword(password);
        try {
            User save = userDao.save(user);
        } catch (Exception e) {
            System.out.println(String.format("user save fail:%s",email)+"messsage:"+e.getMessage());
            e.printStackTrace();
            return false;
        }
        return  true;
    }


    /**
     * 根据token查询用户邮箱
     *
     * @param token 用户Token
     * @return 用户邮箱
     */
    @Override
    public String queryEmail(String token) {
        Token tokenInfo = new Token();
        tokenInfo.setToken(token);
        List<Token> tokens = new ArrayList<>();
        Example<Token> example = Example.of(tokenInfo);
        try {
             tokens = tokenDao.findAll(example);
            if (tokens.isEmpty()) {
                return null;
            }
            System.out.println("token.email:"+tokens.get(0).getEmail());
            return tokens.get(0).getEmail();
        } catch (Exception ex) {
            System.out.println(String.format("Failed to query token information of %s", token));
            ex.printStackTrace();
        }

        return tokens.get(0).getEmail();
    }

    /**
     * 删除Token
     *
     * @param email 用户邮箱
     * @return 操作结果
     */
    private boolean deleteToken(String email) {
        Token token = new Token();
        token.setEmail(email);

        Example<Token> example = Example.of(token);
        try {
            List<Token> tokens = tokenDao.findAll(example);
            for (Token curToken : tokens) {
                tokenDao.delete(token);
            }

            return  true;
        } catch (Exception ex) {
            System.out.println(String.format("Failed to delete tokens of %s", email));
            ex.printStackTrace();
        }

        return false;
    }


    /**
     * 生成Token
     *
     * @param email 用户邮箱
     * @param password 用户密码
     * @return 生成的Token
     */
    @Override
    public String createToken(String email, String password) {
        if (!deleteToken(email)) {
            System.out.println(String.format("Failed to delete old tokens of %s", email));
            return "";
        }

        Token token = new Token();
        token.setEmail(email);
        token.setToken(String.format("%s%s", email, password));

        try {
            tokenDao.save(token);
            return token.getToken();
        } catch (Exception ex) {
            System.out.println(String.format("Failed to save token of %s", email));
            ex.printStackTrace();
        }

        return "";
    }


}
