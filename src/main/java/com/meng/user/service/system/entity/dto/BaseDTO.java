package com.meng.user.service.system.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 大橙子
 * @create 2019/3/25
 * @since 1.0.0
 */
@Data
public abstract class BaseDTO implements Serializable {

    private static final long serialVersionUID = -8902533046347688291L;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String modifiedTime;
}
