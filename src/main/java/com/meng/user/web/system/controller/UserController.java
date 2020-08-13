package com.meng.user.web.system.controller;

import com.meng.user.common.base.Result;
import com.meng.user.common.util.ResultUtil;
import com.meng.user.repository.system.entity.User;
import com.meng.user.service.system.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户 前端控制器
 *
 * @author mengli
 * @create 2020-07-04
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @RequestMapping(value = "/countUser", method = RequestMethod.GET)
    public Result<User> getUser() {

        User user = userService.getUserByUserame("");

        return ResultUtil.success(user);
    }
}

