package com.meng.user.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meng.user.repository.system.entity.UserDO;
import com.meng.user.service.system.entity.dto.UserDTO;
import com.meng.user.web.system.entity.request.UserReq;

import java.util.List;

public interface UserService {

    boolean updatePassword(UserDTO userDTO);

    /**
     * 添加用户-角色之间关系
     *
     * @param userId 用户id
     * @param roleIds 角色ids
     */
    void addCorrelationRoles(Long userId, Long... roleIds);

    /**
     * 移除用户-角色之间关系
     *
     * @param userId 用户id
     * @param roleIds 角色ids
     */
    void removeCorrelationRoles(Long userId, Long... roleIds);

    /**
     * 用户登录判断
     *
     * @param userReq 用户信息
     */
    String login(UserReq userReq);

    /**
     * 查询单个用户
     *
     * @param userId 主键
     * @return 用户
     */
    UserDTO getUser(Long userId);

    /**
     * 查询单个用户
     *
     * @param username 用户名
     * @return 用户
     */
    UserDTO getUserByUsername(String username);

    /**
     * 查询用户集合
     *
     * @param page 分页对象
     * @return 用户集合
     */
    List<UserDTO> listUsers(Page<UserDO> page);

    /**
     * 插入一条记录
     *
     * @param userDTO 实体对象
     * @return 逻辑值
     */
    boolean saveUser(UserDTO userDTO);

    /**
     * 主键存在则更新记录，否则插入一条记录
     *
     * @param userDTO 实体对象
     * @return 逻辑值
     */
    boolean saveOrUpdateUser(UserDTO userDTO);

    /**
     * 修改一条记录
     *
     * @param userDTO 实体对象
     * @return 逻辑值
     */
    boolean updateUser(UserDTO userDTO);

    /**
     * 删除一条记录
     *
     * @param userId 主键
     * @return 逻辑值
     */
    boolean deleteUser(Long userId);

}
