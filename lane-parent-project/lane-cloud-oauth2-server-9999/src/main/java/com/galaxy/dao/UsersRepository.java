package com.galaxy.dao;

import com.galaxy.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author lane
 * @date 2021年07月01日 下午3:48
 */
public interface UsersRepository extends JpaRepository<Users,Long> {

    Users findByUsername(String username);

}
