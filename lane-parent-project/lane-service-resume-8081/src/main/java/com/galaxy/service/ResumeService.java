package com.galaxy.service;

import com.galaxy.pojo.Resume;

/**
 * @author lane
 * @date 2021年06月22日 下午4:39
 */
public interface ResumeService {
    Resume findDefaultResumeByUserId(Long userId);
}
