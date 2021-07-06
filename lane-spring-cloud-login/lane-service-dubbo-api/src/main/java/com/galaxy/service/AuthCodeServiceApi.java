package com.galaxy.service;

/**
 * @author lane
 * @date 2021年06月27日 下午3:52
 */
public interface AuthCodeServiceApi {
    /**
     * 发送验证码到指定的邮箱
     *
     * @param email 指定的邮箱
     * @return 操作结果
     */
    boolean sendCode(String email) throws Exception;

    /**
     * 校验验证码
     *
     * @param email 验证码对应的邮箱
     * @param code 验证码
     * @return 校验结果
     */
    int validateCode(String email, String code);

}
