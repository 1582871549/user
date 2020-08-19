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
public class RoleDTO extends BaseDTO {

    private static final long serialVersionUID = -8532970873838122778L;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色描述
     */
    private String description;
    /**
     * 是否可用(0:false 1:true)
     */
    private Boolean activation;
}