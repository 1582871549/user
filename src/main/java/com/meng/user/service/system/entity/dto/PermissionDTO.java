package com.meng.user.service.system.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 大橙子
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PermissionDTO extends BaseDTO {

    private static final long serialVersionUID = 7225989106023399374L;
    /**
     * 资源id
     */
    private Long permissionId;
    /**
     * 资源名称
     */
    private String permissionName;
    /**
     * 描述
     */
    private String description;
    /**
     * 父元素id
     */
    private Long parentId;
    /**
     * url
     */
    private String url;
    /**
     * 操作
     */
    private String operation;
    /**
     * 类型（0：菜单，1：按钮）
     */
    private Integer type;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 图标
     */
    private String icon;
    /**
     * 是否可用(0:false 1:true)
     */
    private Boolean activation;
}