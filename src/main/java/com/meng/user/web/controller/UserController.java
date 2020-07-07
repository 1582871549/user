package com.meng.user.web.controller;

import com.meng.user.common.util.ApiResponseUtil;
import com.meng.user.service.UserService;
import com.meng.user.web.entity.ApiResponse;
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
    public ApiResponse getUser() {
        return ApiResponseUtil.getApiResponse(userService.getUserByUserame(""));
    }
}

