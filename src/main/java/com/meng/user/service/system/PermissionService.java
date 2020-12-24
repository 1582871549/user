package com.meng.user.service.system;

import com.meng.user.repository.system.entity.PermissionDO;

import java.util.List;

public interface PermissionService {

    /**
     * 查询资源集合
     *
     * @param roleId 角色id
     *
     * @return 资源集合
     */
    List<PermissionDO> listPermissions(Long roleId);
}
