package com.meng.user.service.impl;

import com.meng.user.repository.system.entity.Role;
import com.meng.user.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色 服务实现类
 *
 * @author mengli
 * @create 2020-07-04
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Override
    public int countRole() {
        return 0;
    }

    @Override
    public Role getRoleById(Long id) {
        return null;
    }

    @Override
    public List<Role> listRoles() {
        return null;
    }

    @Override
    public List<Role> listRoles(Role entity) {
        return null;
    }

    @Override
    public boolean saveRole(Role entity) {
        return false;
    }

    @Override
    public boolean saveBatch(List<Role> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateRoleById(Role entity) {
        return false;
    }

    @Override
    public boolean updateRole(Role entity) {
        return false;
    }

    @Override
    public boolean updateBatch(List<Role> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean removeRoleById(Long id) {
        return false;
    }

    @Override
    public boolean remove(Role entity) {
        return false;
    }
}
