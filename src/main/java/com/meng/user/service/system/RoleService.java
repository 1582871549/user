package com.meng.user.service.system;

import com.meng.user.service.system.entity.dto.RoleDTO;

import java.util.List;
import java.util.Set;

public interface RoleService {

    /**
     * 添加角色-权限之间关系
     *
     * @param roleId 角色id
     * @param permissionIds 权限ids
     */
    void addCorrelationPermissions(Long roleId, Long... permissionIds);

    /**
     * 移除角色-权限之间关系
     *
     * @param roleId 角色id
     * @param permissionIds 权限ids
     */
    void removeCorrelationPermissions(Long roleId, Long... permissionIds);

    /**
     * 查询单个角色
     *
     * @param roleId 主键
     * @return 角色
     */
    RoleDTO getRole(Long roleId);

    /**
     * 查询单个角色
     *
     * @param roleName 角色名称
     * @return 角色
     */
    RoleDTO getRole(String roleName);

    /**
     * 查询角色集合
     *
     * @return 角色集合
     */
    List<RoleDTO> listRoles();

    /**
     * 查询角色集合
     *
     * @param userId 用户id
     * @return 角色集合
     */
    List<RoleDTO> listRoles(Long userId);

    /**
     * 查询角色集合
     *
     * @param userId 用户id
     * @return 角色集合
     */
    Set<String> listRoleNames(Long userId);

    /**
     * 插入一条记录
     *
     * @param roleDTO 实体对象
     * @return 逻辑值
     */
    boolean saveRole(RoleDTO roleDTO);

    /**
     * 主键存在则更新记录，否则插入一条记录
     *
     * @param roleDTO 实体对象
     * @return 逻辑值
     */
    boolean saveOrUpdateRole(RoleDTO roleDTO);

    /**
     * 修改一条记录
     *
     * @param roleDTO 实体对象
     * @return 逻辑值
     */
    boolean updateRole(RoleDTO roleDTO);

    /**
     * 删除一条记录
     *
     * @param roleId 主键
     * @return 逻辑值
     */
    boolean deleteRole(Long roleId);
}
