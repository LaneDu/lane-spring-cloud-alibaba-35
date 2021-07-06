package com.galaxy.service.impl;

import com.galaxy.dao.ResumeDao;
import com.galaxy.pojo.Resume;
import com.galaxy.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author lane
 * @date 2021年06月22日 下午4:40
 */
@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    ResumeDao resumeDao;

    @Override
    public Resume findDefaultResumeByUserId(Long userId) {
        Resume resume = new Resume();
        resume.setUserId(userId);
        // 查询默认简历
        resume.setIsDefault(1);
        Example<Resume> example = Example.of(resume);

        Optional<Resume> one = resumeDao.findOne(example);
        if (one.isEmpty()){
            System.out.println("数据为空！");
            return null;
        }

        return resumeDao.findOne(example).get();
    }
}
