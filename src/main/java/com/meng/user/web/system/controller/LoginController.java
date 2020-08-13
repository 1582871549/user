package com.meng.user.web.system.controller;

import com.meng.user.common.annotation.UserLoginToken;
import com.meng.user.common.base.Result;
import com.meng.user.common.util.ResultUtil;
import com.meng.user.repository.system.entity.User;
import com.meng.user.service.system.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户 前端控制器
 *
 * @author mengli
 * @create 2020-07-06
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class LoginController {

    private final UserService userService;

    @UserLoginToken
    @RequestMapping(value = "/countUser", method = RequestMethod.GET)
    public int countUser() {
        return userService.countUser();
    }


    /**
     * 登陆接口
     *
     * @return token
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<String> login(@RequestBody User userReq) {

        String token = userService.login(userReq);

        return ResultUtil.success(token);
    }

}

