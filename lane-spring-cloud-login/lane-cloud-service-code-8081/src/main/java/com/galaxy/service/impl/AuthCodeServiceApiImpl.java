package com.galaxy.service.impl;

import com.galaxy.dao.AuthCodeDao;
import com.galaxy.pojo.AuthCodePojo;
import com.galaxy.service.AuthCodeServiceApi;
import com.galaxy.service.SendEmailServiceApi;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author lane
 * @date 2021年06月27日 下午3:53
 */
@Service
public class AuthCodeServiceApiImpl implements AuthCodeServiceApi {
    //验证码最大值
    private final int MAX_CODE = 1000000;
    //验证码最小值
    private final int MIN_CODE = 100000;
    //过期时间
    private final int EXPIRE_TIME = 600000;
    //随机数
//    private Random random = new Random();

    @Reference
    private SendEmailServiceApi SendEmailServiceApi;
    @Autowired
    private AuthCodeDao authCodeDao;


    
    /**
     * 保持验证码信息到数据库
     * @author lane
     * @date 2021/6/27 下午4:04
     * @param email
     * @param code 
     */
    private void saveCode(String email,String code){
        AuthCodePojo authCodePojo = new AuthCodePojo();
        authCodePojo.setCode(code);
        Date date = new Date();
        authCodePojo.setCreateTime(date);
        authCodePojo.setEmail(email);
        Date expireTime = new Date(date.getTime()+EXPIRE_TIME);
        authCodePojo.setExpireTime(expireTime);
        authCodeDao.save(authCodePojo);

    }
    /**
     * 生成并发送验证码
     * @author lane
     * @date 2021/6/27 下午4:12
     * @param email
     * @return boolean
     */
    @Override
    public boolean sendCode(String email)  {
        double random = (Math.random()*(MAX_CODE-MIN_CODE)+MIN_CODE);
        String s = String.valueOf(random);
        //生成6位验证码
        String code = s.substring(0,6);
        System.out.println("code :"+code);

        try {
            //发送验证码
            String sendEmail = SendEmailServiceApi.sendMail(email,"验证码", code);
            //保存验证码
            saveCode(email, code);
        } catch (Exception e) {
            System.out.println("fail to send email or save code"+email);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断验证码是否正确及过期
     * @author lane
     * @date 2021/6/27 下午4:20
     * @param email
     * @param code
     * @return int
     */
    @Override
    public int validateCode(String email, String code) {

        // 搜索验证码记录
        AuthCodePojo authCode = new AuthCodePojo();
        authCode.setEmail(email);
        authCode.setCode(code);
        Example<AuthCodePojo> example = Example.of(authCode);
        Sort sort = Sort.by(Sort.Order.desc("createTime"));
        List<AuthCodePojo> authCodes = Collections.emptyList();
        try {
            authCodes = authCodeDao.findAll(example, sort);
            //验证码是否正确
            if (authCodes.isEmpty()){
                return 1;
            }
        } catch (Exception e) {
            System.out.println(String.format("fail to get code from db:%s ", email));
            e.printStackTrace();

        }
        AuthCodePojo authCodePojo = authCodes.get(0);
        Date expireTime = authCodePojo.getExpireTime();
        Date date = new Date();
        //判断验证码是否过期
        if (date.getTime()>expireTime.getTime()){
            return 2;
        }
        return 0;
    }
}
