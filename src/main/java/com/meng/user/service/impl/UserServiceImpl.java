package com.meng.user.service.impl;

import com.meng.user.repository.system.entity.User;
import com.meng.user.repository.system.mapper.UserMapper;
import com.meng.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户 服务实现类
 *
 * @author mengli
 * @create 2020-07-04
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public int countUser() {
        return 0;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public List<User> listUsers() {
        return null;
    }

    @Override
    public List<User> listUsers(User entity) {
        return null;
    }

    @Override
    public boolean saveUser(User entity) {
        return false;
    }

    @Override
    public boolean saveBatch(List<User> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateUserById(User entity) {
        return false;
    }

    @Override
    public boolean updateUser(User entity) {
        return false;
    }

    @Override
    public boolean updateBatch(List<User> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean removeUserById(Long id) {
        return false;
    }

    @Override
    public boolean remove(User entity) {
        return false;
    }

    @Override
    public boolean checkUser(User entity) {
        return true;
    }

    @Override
    public User getUserByUserame(String username) {

        User user = new User()
                .setUserId(201L)
                .setUsername(username)
                .setNickname("dudu")
                .setMobile("135");

        return user;
    }
}
