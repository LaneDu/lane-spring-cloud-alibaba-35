package com.galaxy.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * Token信息
 */
@Entity
@Table(name = "r_token")
@Data
public class Token {
    /**
     * Token的ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Token对应的邮箱
     */
    private String email;

    /**
     * Token值
     */
    private String token;


}
