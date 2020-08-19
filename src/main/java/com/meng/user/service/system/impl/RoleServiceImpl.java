package com.meng.user.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.meng.user.common.util.BeanCopyUtil;
import com.meng.user.common.util.ResultUtil;
import com.meng.user.repository.system.entity.RoleDO;
import com.meng.user.repository.system.mapper.RoleMapper;
import com.meng.user.service.system.RoleService;
import com.meng.user.service.system.entity.dto.RoleDTO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 大橙子
 * @create 2019/8/8
 * @since 1.0.0
 */
@Transactional(transactionManager = "dataSourceTransactionManager", rollbackFor = Exception.class)
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    @Override
    public void addCorrelationPermissions(Long roleId, Long... permissionIds) {

    }

    @Override
    public void removeCorrelationPermissions(Long roleId, Long... permissionIds) {

    }

    @Override
    public RoleDTO getRole(Long roleId) {

        RoleDO roleDO = roleMapper.selectById(roleId);

        return null;
    }

    /**
     * 查询单个角色
     *
     * @param roleName 角色名称
     * @return 角色
     */
    @Override
    public RoleDTO getRole(String roleName) {
        RoleDO roleDO = roleMapper.selectOne(new QueryWrapper<RoleDO>().eq("roleName", roleName));
        return BeanCopyUtil.copy(roleDO, RoleDTO.class);
    }

    @Override
    public List<RoleDTO> listRoles() {

        List<RoleDO> roleDOS = roleMapper.selectList(null);

        return null;
    }

    @Override
    public List<RoleDTO> listRoles(Long userId) {
        List<RoleDO> roleDOS = roleMapper.listRoles(userId);
        return BeanCopyUtil.copyList(roleDOS, RoleDTO.class);
    }

    /**
     * 查询角色集合
     *
     * @param userId 用户id
     * @return 角色集合
     */
    @Override
    public Set<String> listRoleNames(Long userId) {

        List<RoleDO> roleDOS = roleMapper.listRoles(userId);

        List<RoleDO> list = convertNPE(roleDOS);

        return list.stream()
                .map(RoleDO::getRoleName)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean saveRole(RoleDTO roleDTO) {
        return ResultUtil.returnBool(roleMapper.insert(null));
    }

    @Override
    public boolean saveOrUpdateRole(RoleDTO roleDTO) {
        if (roleDTO == null) {
            return false;
        }
        Long roleId = roleDTO.getRoleId();

        return roleId == null || getRole(roleId) == null ? saveRole(roleDTO) : updateRole(roleDTO);
    }

    @Override
    public boolean updateRole(RoleDTO roleDTO) {
        return ResultUtil.returnBool(roleMapper.updateById(null));
    }

    @Override
    public boolean deleteRole(Long roleId) {
        return ResultUtil.returnBool(roleMapper.deleteById(null));
    }

    private <T> List<T> convertNPE(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list;
    }
}
