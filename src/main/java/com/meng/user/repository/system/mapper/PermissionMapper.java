package com.meng.user.repository.system.mapper;

import org.springframework.stereotype.Repository;

/**
 * 资源 Mapper 接口
 *
 * @author mengli
 * @create 2020-07-04
 */
@Repository
public interface PermissionMapper {

    /**
     * 查询总记录数
     *
     * @return count
     */
    int countPermission();
}

