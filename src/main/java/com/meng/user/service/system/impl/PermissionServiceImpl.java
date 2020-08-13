package com.meng.user.service.system.impl;

import com.meng.user.repository.system.entity.Permission;
import com.meng.user.repository.system.mapper.PermissionMapper;
import com.meng.user.service.system.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资源 服务实现类
 *
 * @author mengli
 * @create 2020-07-04
 */
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionMapper permissionMapper;

    @Override
    public int countPermission() {
        return permissionMapper.countPermission();
    }

    @Override
    public Permission getPermissionById(Long id) {
        return null;
    }

    @Override
    public List<Permission> listPermissions() {
        return null;
    }

    @Override
    public List<Permission> listPermissions(Permission entity) {
        return null;
    }

    @Override
    public boolean savePermission(Permission entity) {
        return false;
    }

    @Override
    public boolean saveBatch(List<Permission> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updatePermissionById(Permission entity) {
        return false;
    }

    @Override
    public boolean updatePermission(Permission entity) {
        return false;
    }

    @Override
    public boolean updateBatch(List<Permission> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean removePermissionById(Long id) {
        return false;
    }

    @Override
    public boolean remove(Permission entity) {
        return false;
    }
}
