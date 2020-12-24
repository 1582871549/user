package com.meng.user.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.meng.user.repository.system.entity.RoleDO;
import com.meng.user.repository.system.mapper.RoleMapper;
import com.meng.user.service.system.RoleService;
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
    public void addCorrelationPermissions(Long roleId, List<Long> permissionIds) {

    }

    @Override
    public void removeCorrelationPermissions(Long roleId, List<Long> permissionIds) {

    }

    @Override
    public RoleDO getRole(String roleName) {
        return roleMapper.selectOne(new QueryWrapper<RoleDO>().eq("roleName", roleName));
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

    private <T> List<T> convertNPE(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list;
    }
}
