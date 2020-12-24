package com.meng.user.service.system.impl;

import com.meng.user.repository.system.entity.PermissionDO;
import com.meng.user.repository.system.mapper.PermissionMapper;
import com.meng.user.service.system.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author mengli
 * @create 2019/12/24
 * @since 1.0.0
 */
@Transactional(transactionManager = "dataSourceTransactionManager", rollbackFor = Exception.class)
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionMapper permissionMapper;

    @Override
    public List<PermissionDO> listPermissions(Long roleId) {
        return permissionMapper.listPermissions(roleId);
    }
}
