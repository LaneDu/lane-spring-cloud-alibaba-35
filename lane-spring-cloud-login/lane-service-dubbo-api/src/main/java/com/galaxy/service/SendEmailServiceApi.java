package com.galaxy.service;

/**
 * @author lane
 * @date 2021年06月27日 上午11:19
 */
public interface SendEmailServiceApi {

    boolean sendMailSimple(String email,String code);
    String sendMail(String email,String title,String code) throws Exception;

}
