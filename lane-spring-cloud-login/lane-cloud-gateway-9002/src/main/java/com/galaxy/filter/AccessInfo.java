package com.galaxy.filter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lane
 * @date 2021年06月27日 下午6:38
 */

public class AccessInfo {

    /**
     * 开始访问的时间
     */
    private volatile long startTime;

    /**
     * 访问次数
     */
    private AtomicInteger accessTimes;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public AtomicInteger getAccessTimes() {
        return accessTimes;
    }

    public void setAccessTimes(AtomicInteger accessTimes) {
        this.accessTimes = accessTimes;
    }

    @Override
    public String toString() {
        return "AccessInfo{" +
                "startTime=" + startTime +
                ", accessTimes=" + accessTimes +
                '}';
    }
}
