package com.galaxy.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author lane
 * @date 2021年06月27日 下午3:48
 */
@Entity
@Table(name="r_auth_code")
@Data
public class AuthCodePojo {
    /**
     * 验证码ID
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 验证码
     */
    private String code;

    /**
     * 创建时间
     */
    @Column(name = "createtime")
    private Date createTime;

    /**
     * 过期时间
     */
    @Column(name = "expiretime")
    private Date expireTime;
}
