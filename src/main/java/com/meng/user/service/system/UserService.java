package com.meng.user.service.system;

import com.meng.user.repository.system.entity.UserDO;

import java.util.List;

public interface UserService {

    boolean updatePassword(UserDO userDO);

    /**
     * 添加用户-角色之间关系
     *
     * @param userId 用户id
     * @param roleIds 角色ids
     */
    boolean addCorrelationRoles(Long userId, List<Long> roleIds);

    /**
     * 移除用户-角色之间关系
     *
     * @param userId 用户id
     * @param roleIds 角色ids
     */
    boolean removeCorrelationRoles(Long userId, List<Long> roleIds);

    UserDO getUserById(Long userId);

    UserDO getUserByUsername(String username);

    List<UserDO> listUsers();

    boolean insertUser(UserDO userDO);

    boolean updateUser(UserDO userDO);

    boolean deleteUser(Long userId);

}
