package com.meng.user.repository.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meng.user.repository.system.entity.UserDO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<UserDO> {

    Boolean getUserLocked(String username);

    void addCorrelationRoles(Long userId, Long[] roleIds);

    void removeCorrelationRoles(Long userId, Long[] roleIds);

    int updatePassword(UserDO userDO);
}
