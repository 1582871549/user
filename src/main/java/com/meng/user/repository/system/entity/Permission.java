package com.meng.user.repository.system.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 资源
 *
 * @author mengli
 * @create 2020-07-04
 */
@Data
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 权限id
     */
    private Long permissionId;
    /**
     * 资源名称
     */
    private String name;
    /**
     * 资源类型
     */
    private String type;
    /**
     * 资源路径
     */
    private String url;
    /**
     * 资源权限
     */
    private String permission;
    /**
     * 父级菜单
     */
    private Long parentId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 是否外部链接
     */
    private Boolean external;
    /**
     * 是否已激活
     */
    private Boolean available;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 添加时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
