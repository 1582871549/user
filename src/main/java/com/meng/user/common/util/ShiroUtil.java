/**
 * FileName: ShiroUtil
 * Author:   大橙子
 * Date:     2019/8/8 17:35
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.meng.user.common.util;

import com.meng.user.repository.system.entity.UserDO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 大橙子
 * @create 2019/8/8
 * @since 1.0.0
 */
public class ShiroUtil {

    private static final String NAMES_DELIMETER = ",";
    /**
     * 散列算法
     */
    private static final String HASH_ALGORITHM_NAME = "MD5";
    /**
     * 循环次数
     */
    private static final int HASH_ITERATIONS = 2;

    /**
     * 获取随机盐值
     *
     * @param length 字节长度，一个字节2位16进制数表示
     * @return
     */
    public static String getRandomSalt(int length) {
        return new SecureRandomNumberGenerator().nextBytes(length).toHex();
    }

    /**
     * shiro密码加密工具类
     *
     * @param credentials 密码
     * @param saltSource  密码盐
     * @return 加密字符串
     */
    public static String md5(String credentials, String saltSource) {
        return new SimpleHash(HASH_ALGORITHM_NAME, credentials, saltSource, HASH_ITERATIONS).toHex();
    }

    /**
     * shiro密码加密工具类
     *
     * @param credentials 密码
     * @param salt  密码盐
     * @return 加密字符串
     */
    public static String sha256(String credentials, String salt) {
        return new SimpleHash("SHA-256", credentials, salt, 2).toHex();
    }

    /**
     * 获取当前 Subject
     *
     * @return subject
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 从shiro获取session
     */
    public static Session getSession() {
        return getSubject().getSession();
    }

    /**
     * 获取shiro指定的sessionKey
     */
    @SuppressWarnings("unchecked")
    public static <T> T getSessionAttribute(String key) {
        Session session = getSession();
        return session != null ? (T) session.getAttribute(key) : null;
    }

    /**
     * 设置shiro指定的sessionKey
     */
    public static void setSessionAttribute(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    /**
     * 移除shiro指定的sessionKey
     */
    public static void removeSessionAttribute(String key) {
        Session session = getSession();
        if (session != null) {
            session.removeAttribute(key);
        }
    }

    /**
     * 认证通过或已记住的用户。与guset搭配使用。
     *
     * @return 用户：true，否则 false
     */
    public static boolean isUser() {
        Subject subject = getSubject();
        return subject != null && subject.getPrincipal() != null;
    }

    /**
     * 验证当前用户是否为“访客”，即未认证（包含未记住）的用户。用user搭配使用
     *
     * @return 访客：true，否则false
     */
    public static boolean isGuest() {
        return !isUser();
    }

    /**
     * 已认证通过的用户，不包含已记住的用户，这是与user标签的区别所在。与notAuthenticated搭配使用
     *
     * @return 通过身份验证：true，否则false
     */
    public static boolean isAuthenticated() {
        Subject subject = getSubject();
        return subject != null && subject.isAuthenticated();
    }

    /**
     * 未认证通过用户，与authenticated标签相对应。与guest标签的区别是，该标签包含已记住用户
     *
     * @return 没有通过身份验证：true，否则false
     */
    public static boolean notAuthenticated() {
        return !isAuthenticated();
    }

    /**
     * 验证当前用户是否属于该角色？,使用时与lacksRole 搭配使用
     *
     * @param roleName 角色名
     * @return 属于该角色：true，否则false
     */
    public static boolean hasRole(String roleName) {
        Subject subject = getSubject();
        return subject != null
                && roleName != null
                && roleName.length() > 0
                && subject.hasRole(roleName);
    }

    /**
     * 与hasRole标签逻辑相反，当用户不属于该角色时验证通过。
     *
     * @param roleName 角色名
     * @return 不属于该角色：true，否则false
     */
    public static boolean lacksRole(String roleName) {
        return !hasRole(roleName);
    }

    /**
     * 验证当前用户是否属于以下任意一个角色。
     *
     * @param roleNames 角色列表
     * @return 属于:true,否则false
     */
    public static boolean hasAnyRoles(String roleNames) {
        boolean hasAnyRole = false;
        Subject subject = getSubject();
        if (subject != null && roleNames != null && roleNames.length() > 0) {
            for (String role : roleNames.split(NAMES_DELIMETER)) {
                if (subject.hasRole(role.trim())) {
                    hasAnyRole = true;
                    break;
                }
            }
        }
        return hasAnyRole;
    }

    /**
     * 验证当前用户是否属于以下所有角色。
     *
     * @param roleNames 角色列表
     * @return 属于:true,否则false
     */
    public static boolean hasAllRoles(String roleNames) {
        boolean hasAllRole = true;
        Subject subject = getSubject();
        if (subject != null && roleNames != null && roleNames.length() > 0) {
            for (String role : roleNames.split(NAMES_DELIMETER)) {
                if (!subject.hasRole(role.trim())) {
                    hasAllRole = false;
                    break;
                }
            }
        }
        return hasAllRole;
    }

    /**
     * 验证当前用户是否拥有指定权限,使用时与lacksPermission 搭配使用
     *
     * @param permission 权限名
     * @return 拥有权限：true，否则false
     */
    public static boolean hasPermission(String permission) {

        Subject subject = getSubject();
        return subject != null
                && permission != null
                && permission.length() > 0
                && subject.isPermitted(permission);
    }

    /**
     * 与hasPermission标签逻辑相反，当前用户没有制定权限时，验证通过。
     *
     * @param permission 权限名
     * @return 拥有权限：true，否则false
     */
    public static boolean lacksPermission(String permission) {
        return !hasPermission(permission);
    }

    /**
     * 获取封装的 ShiroUser
     *
     * @return ShiroUser
     */
    public static UserDO getUser() {
        if (isGuest()) {
            return null;
        } else {
            return (UserDO) getSubject().getPrincipals().getPrimaryPrincipal();
        }
    }

    /**
     * 输出当前用户信息，通常为登录帐号信息。
     *
     * @return 当前用户信息
     */
    public static String getPrimaryPrincipal() {
        Subject subject = getSubject();
        if (subject != null) {
            return String.valueOf(subject.getPrincipal());
        }
        return "null";
    }
}
