package com.meng.user.repository.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meng.user.repository.system.entity.RoleDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends BaseMapper<RoleDO> {

    /**
     * 查询角色集合
     *
     * @param userId 用户id
     * @return 角色集合
     */
    List<RoleDO> listRoles(Long userId);
}
