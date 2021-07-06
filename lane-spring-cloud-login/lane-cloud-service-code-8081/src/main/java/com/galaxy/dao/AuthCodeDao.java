package com.galaxy.dao;

import com.galaxy.pojo.AuthCodePojo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lane
 * @date 2021年06月27日 下午3:48
 */
public interface AuthCodeDao extends JpaRepository<AuthCodePojo, Long> {
}
