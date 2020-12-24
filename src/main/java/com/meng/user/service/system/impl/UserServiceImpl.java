package com.meng.user.service.system.impl;

import com.meng.user.common.util.ShiroUtil;
import com.meng.user.repository.system.entity.UserDO;
import com.meng.user.repository.system.mapper.UserMapper;
import com.meng.user.service.system.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 大橙子
 * @create 2019/8/8
 * @since 1.0.0
 */
@Transactional(transactionManager = "dataSourceTransactionManager", rollbackFor = Exception.class)
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public boolean updatePassword(UserDO userDO) {
        return false;
    }

    @Override
    public boolean addCorrelationRoles(Long userId, List<Long> roleIds) {
        return false;
    }

    @Override
    public boolean removeCorrelationRoles(Long userId, List<Long> roleIds) {
        return false;
    }


    @Override
    public UserDO getUserById(Long userId) {
        return null;
    }

    @Override
    public UserDO getUserByUsername(String username) {
        return null;
    }

    @Override
    public List<UserDO> listUsers() {
        return null;
    }

    @Override
    public boolean insertUser(UserDO userDO) {
        return false;
    }

    @Override
    public boolean updateUser(UserDO userDO) {
        return false;
    }

    @Override
    public boolean deleteUser(Long userId) {
        return true;
    }

    /**
     * 使用加密算法对密码进行加密
     *
     * @param password 密码
     * @param salt 盐
     * @return 加密后的密码
     */
    public String encryptionPassword(String password, String salt) {
        return ShiroUtil.sha256(password, salt);
    }
}
