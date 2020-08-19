package com.meng.user.service.system.impl;

import com.meng.user.common.util.BeanCopyUtil;
import com.meng.user.common.util.ResultUtil;
import com.meng.user.repository.system.entity.PermissionDO;
import com.meng.user.repository.system.mapper.PermissionMapper;
import com.meng.user.service.system.PermissionService;
import com.meng.user.service.system.entity.dto.PermissionDTO;
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
    public PermissionDTO getPermission(Long permissionId) {

        PermissionDO permissionDO = permissionMapper.selectById(permissionId);

        return null;
    }

    @Override
    public List<PermissionDTO> listPermissions() {

        List<PermissionDO> permissionDOS = permissionMapper.selectList(null);

        return null;
    }

    @Override
    public List<PermissionDTO> listPermissions(Long roleId) {
        List<PermissionDO> permissionDOS = permissionMapper.listPermissions(roleId);
        return BeanCopyUtil.copyList(permissionDOS, PermissionDTO.class);
    }

    @Override
    public boolean savePermission(PermissionDTO permissionDTO) {
        return ResultUtil.returnBool(permissionMapper.insert(null));
    }

    @Override
    public boolean saveOrUpdatePermission(PermissionDTO permissionDTO) {

        if (permissionDTO == null) {
            return false;
        }
        Long permissionId = permissionDTO.getPermissionId();

        return permissionId == null || getPermission(permissionId) == null ? savePermission(permissionDTO) : updatePermission(permissionDTO);
    }

    @Override
    public boolean updatePermission(PermissionDTO permissionDTO) {
        return ResultUtil.returnBool(permissionMapper.updateById(null));
    }

    @Override
    public boolean deletePermission(Long permissionId) {
        return ResultUtil.returnBool(permissionMapper.deleteById(permissionId));
    }
}
