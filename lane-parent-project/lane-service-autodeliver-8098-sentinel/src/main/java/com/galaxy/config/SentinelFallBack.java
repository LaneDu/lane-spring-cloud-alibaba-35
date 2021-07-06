package com.galaxy.config;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author lane
 * @date 2021年07月03日 上午10:52
 */
public class SentinelFallBack {
    // 整体要求和当时Hystrix一样，这里还需要在形参中添加BlockException参数，用于接收异常
    // 注意：方法是静态的
    public static Integer handleException(Long userId, BlockException blockException) {
        return -100;
    }
    //这里是Java 出现error的时候自定义的兜底方法
    public static Integer handleError(Long userId) {
        return -500;
    }
}
