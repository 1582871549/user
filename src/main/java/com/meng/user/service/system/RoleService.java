package com.meng.user.service.system;

import com.meng.user.repository.system.entity.RoleDO;

import java.util.List;
import java.util.Set;

public interface RoleService {

    /**
     * 添加角色-权限之间关系
     *
     * @param roleId 角色id
     * @param permissionIds 权限ids
     */
    void addCorrelationPermissions(Long roleId, List<Long> permissionIds);

    /**
     * 移除角色-权限之间关系
     *
     * @param roleId 角色id
     * @param permissionIds 权限ids
     */
    void removeCorrelationPermissions(Long roleId, List<Long> permissionIds);

    /**
     * 查询单个角色
     *
     * @param roleName 角色名称
     * @return 角色
     */
    RoleDO getRole(String roleName);

    /**
     * 查询角色集合
     *
     * @param userId 用户id
     * @return 角色集合
     */
    Set<String> listRoleNames(Long userId);

}
