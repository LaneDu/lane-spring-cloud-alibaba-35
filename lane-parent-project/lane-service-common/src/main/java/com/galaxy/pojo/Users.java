package com.galaxy.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lane
 * @date 2021年07月01日 下午3:43
 */
@Data
@Entity
@Table(name="users")
public class Users {

        @Id
        private Long id;
        private String username;
        private String password;
    }


