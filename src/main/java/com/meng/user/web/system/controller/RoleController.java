package com.meng.user.web.system.controller;

import com.meng.user.service.system.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色 前端控制器
 *
 * @author mengli
 * @create 2020-07-04
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    // @RequestMapping(value = "/listRoles", method = RequestMethod.GET)
    // public Result<List<Role>> listRoles() {
    //
    //     List<Role> roles = roleService.listRoles();
    //
    //     return ResultUtil.success(roles);
    // }
}

