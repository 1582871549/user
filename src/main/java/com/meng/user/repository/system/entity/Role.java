package com.meng.user.repository.system.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色
 *
 * @author mengli
 * @create 2020-07-04
 */
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 角色名
     */
    private String name;
    /**
     * 角色描述
     */
    private String description;
    /**
     * 是否已激活
     */
    private Boolean available;
    /**
     * 添加时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
