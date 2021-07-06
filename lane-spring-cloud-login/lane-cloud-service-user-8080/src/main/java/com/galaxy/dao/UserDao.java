package com.galaxy.dao;

import com.galaxy.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lane
 * @date 2021年06月26日 下午3:08
 */
public interface UserDao extends JpaRepository<User, Long> {
}
