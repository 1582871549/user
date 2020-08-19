package com.meng.user.web.system.controller;

import com.meng.user.common.annotation.UserLoginToken;
import com.meng.user.common.base.ResultBase;
import com.meng.user.common.util.ResultUtil;
import com.meng.user.service.system.UserService;
import com.meng.user.service.system.entity.dto.UserDTO;
import com.meng.user.web.system.entity.request.UserReq;
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
        // return userService.countUser();
        return 0;
    }

    /**
     * 登陆接口
     *
     * @return token
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultBase login(@RequestBody UserReq userReq) {

        String token = userService.login(userReq);

        return ResultUtil.success(token);
    }

    /**
     * 登陆接口
     *
     * @return token
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResultBase register(@RequestBody UserDTO userDTO) {

        userService.saveUser(userDTO);

        return ResultUtil.success();
    }

}

