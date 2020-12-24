package com.meng.user.shiro;

import com.meng.user.common.enums.ResultEnum;
import com.meng.user.common.exception.BusinessException;
import com.meng.user.repository.system.entity.UserDO;
import com.meng.user.service.system.RoleService;
import com.meng.user.service.system.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 大橙子
 * @create 2019/3/25
 * @since 1.0.0
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
     * 仅支持UsernamePasswordToken类型的Token
     */
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 自定义名称
     */
    public String getName() {
        return this.getClass().getName();
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // 获取用户的输入的账号
        String username = String.valueOf(token.getPrincipal());

        if (StringUtils.isBlank(username)) {
            log.debug("current token's username is null.");
            throw new AuthenticationException();
        } else {
            log.debug("current token's : {}", username);
        }

        System.out.println("当前登陆密码" + token.getCredentials());

        // 通过username从数据库中查找 User对象，如果找到，没找到.
        // 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserDO userDO = userService.getUserByUsername(username);

        // 身份验证是否成功
        if (userDO == null) {
            throw new BusinessException(ResultEnum.USER_NOT_EXIST);
        }

        if (userDO.getLocked()) {
            throw new BusinessException(ResultEnum.USER_LOCKED);
        }

        return new SimpleAuthenticationInfo(
                username,
                userDO.getPassword(),
                ByteSource.Util.bytes(userDO.getSalt()),
                getName()
        );
    }

    /**
     * 此方法调用hasRole,hasPermission的时候才会进行回调.
     * <p>
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     *
     * @param principals 身份
     * @return 角色-资源
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        /*
         * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
         * 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
         * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了，
         * 缓存过期之后会再次执行。
         */
        log.info("into doGetAuthorizationInfo method ... ");

        Long userId = getUserId(principals);

        if (userId == null || userId == 0) {
            return new SimpleAuthorizationInfo();
        }

        return setRoles(userId);
    }

    private Long getUserId(PrincipalCollection principals) {

        String username = String.valueOf(getAvailablePrincipal(principals));

        if (StringUtils.isBlank(username)) {
            return null;
        }

        UserDO userDO = userService.getUserByUsername(username);

        if (userDO == null) {
            return null;
        }

        return userDO.getUserId();
    }

    public SimpleAuthorizationInfo setRoles(Long userId) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleService.listRoleNames(userId));
        return info;
    }

}
