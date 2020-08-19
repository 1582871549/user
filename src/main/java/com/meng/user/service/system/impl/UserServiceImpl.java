package com.meng.user.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meng.user.common.util.BeanCopyUtil;
import com.meng.user.common.util.JwtHelper;
import com.meng.user.common.util.ResultUtil;
import com.meng.user.common.util.ShiroUtil;
import com.meng.user.repository.system.entity.UserDO;
import com.meng.user.repository.system.mapper.UserMapper;
import com.meng.user.service.system.UserService;
import com.meng.user.service.system.entity.dto.UserDTO;
import com.meng.user.web.system.entity.request.UserReq;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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

    public boolean updatePassword(UserDTO userDTO) {

        String salt = ShiroUtil.getRandomSalt(16);
        String hashPassword = encryptionPassword(userDTO.getPassword(), salt);

        UserDO userDO = BeanCopyUtil.copy(userDTO, UserDO.class);

        userDO.setSalt(salt);
        userDO.setPassword(hashPassword);

        return ResultUtil.returnBool(userMapper.updatePassword(userDO));
    }

    @Override
    public void addCorrelationRoles(Long userId, Long... roleIds) {
        userMapper.addCorrelationRoles(userId, roleIds);
    }

    @Override
    public void removeCorrelationRoles(Long userId, Long... roleIds) {
        userMapper.removeCorrelationRoles(userId, roleIds);
    }

    @Override
    public String login(UserReq userReq) {

        String username = userReq.getUsername();
        String password = userReq.getPassword();

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        Subject currentUser = SecurityUtils.getSubject();

        currentUser.login(token);

        UserDTO userDTO = getUserByUsername(username);

        return JwtHelper.sign(userDTO);
    }

    @Override
    public UserDTO getUser(Long userId) {
        UserDO userDO = userMapper.selectById(userId);
        return BeanCopyUtil.copy(userDO, UserDTO.class);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        UserDO userDO = userMapper.selectOne(new QueryWrapper<UserDO>().eq("username", username));
        return BeanCopyUtil.copy(userDO, UserDTO.class);
    }

    @Override
    public List<UserDTO> listUsers(Page<UserDO> page) {

        IPage<UserDO> iPage = userMapper.selectPage(page, null);

        List<UserDO> records = iPage.getRecords();

        return BeanCopyUtil.copyList(records, UserDTO.class);
    }

    @Override
    public boolean saveUser(UserDTO userDTO) {

        UserDO userDO = BeanCopyUtil.copy(userDTO, UserDO.class);

        String salt = ShiroUtil.getRandomSalt(16);
        String hashPassword = encryptionPassword(userDO.getPassword(), salt);

        userDO.setSalt(salt);
        userDO.setPassword(hashPassword);

        return ResultUtil.returnBool(userMapper.insert(userDO));
    }

    @Override
    public boolean saveOrUpdateUser(UserDTO userDTO) {
        if (userDTO == null) {
            return false;
        }
        Long userId = userDTO.getUserId();
        return userId == null || getUser(userId) == null ? saveUser(userDTO) : updateUser(userDTO);
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        UserDO userDO = BeanCopyUtil.copy(userDTO, UserDO.class);
        return ResultUtil.returnBool(userMapper.updateById(userDO));
    }

    @Override
    public boolean deleteUser(Long userId) {
        return ResultUtil.returnBool(userMapper.deleteById(userId));
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
