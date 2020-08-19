package com.meng.user.common.interceptor;

import com.meng.user.common.annotation.PassToken;
import com.meng.user.common.annotation.UserLoginToken;
import com.meng.user.common.util.JwtHelper;
import com.meng.user.service.system.UserService;
import com.meng.user.service.system.entity.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义token拦截器
 *
 * @author mengli
 * @create 2020-07-06
 */
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /**
         * 主要流程:
         * 1.从 http 请求头中取出 token，
         * 2.判断是否映射到方法
         * 3.检查是否有passtoken注释，有则跳过认证
         * 4.检查有没有需要用户登录的注解，有则需要取出并验证
         * 5.认证通过则可以访问，不通过会报相关错误信息
         */

        // 若是不是映射到方法直接经过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        String token = request.getHeader("token");

        log.info("[登录校验拦截器] header-token:{}", token);

        return authentication(handler, token);
    }

    private boolean authentication(Object handler, String token) {

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        boolean hasPassAnnotation = handlerMethod.hasMethodAnnotation(PassToken.class);

        //检查是否有passtoken注释，有则跳过认证
        if (hasPassAnnotation) {
            return true;
        }

        boolean hasLoginTokenAnnotation = handlerMethod.hasMethodAnnotation(UserLoginToken.class);

        //检查有没有需要用户权限的注解
        if (hasLoginTokenAnnotation) {

            if (StringUtils.isBlank(token)) {
                log.info("无token，请重新登录");
                throw new RuntimeException("无token，请重新登录");
            }

            String username = JwtHelper.getUsername(token);

            UserDTO user = userService.getUserByUsername(username);

            if (user == null) {
                log.info("用户不存在，请重新登录");
                throw new RuntimeException("用户不存在，请重新登录");
            }

            boolean result = JwtHelper.verify(token, user.getPassword());

            log.info("[登录校验拦截器] 校验JWT有效性 - 返回结果:{}", result);

            return result;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
