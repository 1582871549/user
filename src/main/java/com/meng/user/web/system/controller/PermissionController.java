package com.meng.user.web.system.controller;

import com.meng.user.service.system.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 资源 前端控制器
 *
 * @author mengli
 * @create 2020-07-04
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/permission")
public class PermissionController {

    private final PermissionService permissionService;

    // @RequestMapping(value = "/countPermission", method = RequestMethod.GET)
    // public int countPermission() {
    //     return this.permissionService.countPermission();
    // }
}

