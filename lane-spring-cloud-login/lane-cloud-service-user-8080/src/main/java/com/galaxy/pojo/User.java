package com.galaxy.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author lane
 * @date 2021年06月26日 下午2:43
 */
@Data
@Entity
@Table(name = "r_user")
public class User {

    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String sex;
    private String phone;
    private String email;
    private String createTime; // 创建时间


}
