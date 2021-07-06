package com.galaxy.dao;

import com.galaxy.pojo.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lane
 * @date 2021年06月22日 下午4:37
 */
public interface ResumeDao extends JpaRepository<Resume,Long> {


}
