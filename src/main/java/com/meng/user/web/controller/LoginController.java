package com.meng.user.web.controller;

import com.meng.user.common.enums.ApiResponseEnum;
import com.meng.user.common.util.ApiResponseUtil;
import com.meng.user.common.util.JwtUtil;
import com.meng.user.repository.system.entity.User;
import com.meng.user.service.UserService;
import com.meng.user.web.entity.ApiResponse;
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
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

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
    public ApiResponse login(@RequestBody User userReq) {

        String username = userReq.getUsername();

        // 身份验证是否成功
        boolean isSuccess = userService.checkUser(userReq);

        if (isSuccess) {
            User user = userService.getUserByUserame(username);
            if (user != null) {
                //返回token
                String token = JwtUtil.sign(userReq);
                if (token != null) {
                    return ApiResponseUtil.getApiResponse(token);
                }
            }
        }
        //返回登陆失败消息
        return ApiResponseUtil.getApiResponse(ApiResponseEnum.LOGIN_FAIL);
    }

}

