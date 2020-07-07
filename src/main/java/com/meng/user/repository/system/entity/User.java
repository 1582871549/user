package com.meng.user.repository.system.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户
 *
 * @author mengli
 * @create 2020-07-04
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * QQ
     */
    private String qq;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 超级管理员、管理员、普通用户
     */
    private Integer userType;
    /**
     * 注册IP
     */
    private String regIp;
    /**
     * 最近登录IP
     */
    private String lastLoginIp;
    /**
     * 最近登录时间
     */
    private String lastLoginTime;
    /**
     * 登录次数
     */
    private Integer loginCount;
    /**
     * 用户备注
     */
    private String remark;
    /**
     * 用户状态
     */
    private Integer status;
    /**
     * 注册时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
