package com.meng.user.repository.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meng.user.repository.system.entity.PermissionDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper extends BaseMapper<PermissionDO> {

    /**
     * 查询资源集合
     *
     * @param roleId 角色id
     * @return 权限资源
     */
    List<PermissionDO> listPermissions(Long roleId);
}
