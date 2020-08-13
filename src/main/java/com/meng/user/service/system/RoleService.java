package com.meng.user.service.system;

import com.meng.user.repository.system.entity.Role;

import java.util.List;

/**
 * 角色 服务类
 *
 * @author mengli
 * @create 2020-07-04
 */
public interface RoleService {

    /**
     * 查询总记录数
     *
     * @return 总记录数
     */
    int countRole();

    /**
     * 查询单条记录
     *
     * @param id 主键
     * @return 实体
     */
    Role getRoleById(Long id);

    /**
     * 查询集合
     *
     * @return 集合
     */
    List<Role> listRoles();

    /**
     * 根据 entity 条件，查询集合
     *
     * @param entity 实体条件对象
     * @return 集合
     */
    List<Role> listRoles(Role entity);

    /**
     * 插入一条记录
     *
     * @param entity 实体对象
     * @return 逻辑值
     */
    boolean saveRole(Role entity);

    /**
     * 批量插入
     *
     * @param entityList 实体对象集合
     * @param batchSize  插入数量
     */
    boolean saveBatch(List<Role> entityList, int batchSize);

    /**
     * 修改一条记录
     *
     * @param entity 实体对象
     * @return 逻辑值
     */
    boolean updateRoleById(Role entity);

    /**
     * 根据 entity 条件，修改记录
     *
     * @param entity 实体条件对象
     * @return 逻辑值
     */
    boolean updateRole(Role entity);

    /**
     * 批量修改
     *
     * @param entityList 实体对象集合
     * @param batchSize  修改数量
     */
    boolean updateBatch(List<Role> entityList, int batchSize);

    /**
     * 删除一条记录
     *
     * @param id 主键
     * @return 逻辑值
     */
    boolean removeRoleById(Long id);

    /**
     * 根据 entity 条件，删除记录
     *
     * @param entity 实体条件对象
     */
    boolean remove(Role entity);

}