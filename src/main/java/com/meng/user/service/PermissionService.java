package com.meng.user.service;

import com.meng.user.repository.system.entity.Permission;

import java.util.List;

/**
 * 资源 服务类
 *
 * @author mengli
 * @create 2020-07-04
 */
public interface PermissionService {

    /**
     * 查询总记录数
     *
     * @return 总记录数
     */
    int countPermission();

    /**
     * 查询单条记录
     *
     * @param id 主键
     * @return 实体
     */
    Permission getPermissionById(Long id);

    /**
     * 查询集合
     *
     * @return 集合
     */
    List<Permission> listPermissions();

    /**
     * 根据 entity 条件，查询集合
     *
     * @param entity 实体条件对象
     * @return 集合
     */
    List<Permission> listPermissions(Permission entity);

    /**
     * 插入一条记录
     *
     * @param entity 实体对象
     * @return 逻辑值
     */
    boolean savePermission(Permission entity);

    /**
     * 批量插入
     *
     * @param entityList 实体对象集合
     * @param batchSize 插入数量
     */
    boolean saveBatch(List<Permission> entityList, int batchSize);

    /**
     * 修改一条记录
     *
     * @param entity 实体对象
     * @return 逻辑值
     */
    boolean updatePermissionById(Permission entity);

    /**
     * 根据 entity 条件，修改记录
     *
     * @param entity 实体条件对象
     * @return 逻辑值
     */
    boolean updatePermission(Permission entity);

    /**
     * 批量修改
     *
     * @param entityList 实体对象集合
     * @param batchSize 修改数量
     */
    boolean updateBatch(List<Permission> entityList, int batchSize);

    /**
     * 删除一条记录
     *
     * @param id 主键
     * @return 逻辑值
     */
    boolean removePermissionById(Long id);

    /**
     * 根据 entity 条件，删除记录
     *
     * @param entity 实体条件对象
     */
    boolean remove(Permission entity);

}