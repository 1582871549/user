package com.meng.user.common.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.meng.user.shiro.CustomRolePermissionResolver;
import com.meng.user.shiro.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 大橙子
 * @TODO 多个realm配置  在securityManager中   以及多realm的认证策略
 * @date 2019/3/25
 * @since 1.0.0
 */
@Order(1)
@Configuration
public class ShiroConfig {

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是会报错的，
     * 因为在初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * <p>
     * Filter Chain定义说明
     * 1、一个URL可以配置多个Filter，使用逗号分隔
     * 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     * <p>
     * hiroFilterFactoryBean，是个factorybean，为了生成ShiroFilter。
     * 它主要保持了三项数据，securityManager，filters，filterChainDefinitionManager。
     */
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {

        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);

        /*
         * 配置自定义过滤器
         *
         * 如果自定义filter导入异常, 直接new放入即可
         */
        // Map<String, Filter> filtersMap = bean.getFilters();
        // filtersMap.put("custom", customFilter);
        // filtersMap.put("kaptcha", kaptchaFilter);
        // bean.setFilters(filtersMap);

        /*
         * 配置自定义拦截器
         *
         * authc:所有url都必须认证通过才可以访问
         * anon:所有url都都可以匿名访问
         */
        Map<String, String> interceptsMap = new LinkedHashMap<>();

        interceptsMap.put("/index", "user");
        interceptsMap.put("/logout", "logout");
        // map.put("/**", "anon");
        interceptsMap.put("/user/**", "authc");
        interceptsMap.put("/home/**", "anon");
        interceptsMap.put("/static/**", "anon");
        interceptsMap.put("/**", "authc");

        /*
         * 配置用户登陆页面
         * 配置成功跳转路径
         * 配置未经授权页面
         */
        bean.setLoginUrl("/home/login");
        bean.setSuccessUrl("/index");
        // bean.setUnauthorizedUrl("/error/403_error.html");
        bean.setUnauthorizedUrl("/403");
        bean.setFilterChainDefinitionMap(interceptsMap);
        return bean;
    }

    /**
     * 权限管理，这个类组合了登陆，登出，权限，session的处理，是个比较重要的类。
     *
     * @param userRealm      自定义用户域
     * @param ehCacheManager 缓存管理器
     * @return securityManager
     */
    @Bean(name = "securityManager")
    public SecurityManager securityManager(UserRealm userRealm,
                                           EhCacheManager ehCacheManager,
                                           ModularRealmAuthorizer modularRealmAuthorizer,
                                           RememberMeManager rememberMeManager) {

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setCacheManager(ehCacheManager);
        securityManager.setAuthorizer(modularRealmAuthorizer);
        securityManager.setRememberMeManager(rememberMeManager);
        return securityManager;
    }

    /**
     * LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，
     * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。
     * 主要是AuthorizingRealm类的子类，以及EhCacheManager类。
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 用户认证类
     *
     * @return userRealm
     */
    @Bean(name = "userRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public UserRealm userRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return userRealm;
    }

    /**
     * HashedCredentialsMatcher，这个类是为了对密码进行编码的，防止密码在数据库里明码保存，
     * 这个类也负责在登陆时form中输入的密码进行编码。
     *
     * @return hashedCredentialsMatcher
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(ShiroProperties shiroProperties) {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(shiroProperties.getPassword().getHashAlgorithm());
        hashedCredentialsMatcher.setHashIterations(shiroProperties.getPassword().getHashIterations());
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(shiroProperties.getPassword().isStoredCredentialsHexEncoded());

        System.out.println("===================");
        System.out.println(hashedCredentialsMatcher);
        System.out.println("===================");
        return hashedCredentialsMatcher;
    }

    /**
     * 权限 解析器
     * <p>
     * 根据权限操作符, 创建不同的权限实例
     *
     * @return 权限 解析器
     */
    @Bean(name = "modularRealmAuthorizer")
    public ModularRealmAuthorizer modularRealmAuthorizer(PermissionResolver permissionResolver,
                                                         CustomRolePermissionResolver customRolePermissionResolver) {
        ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
        authorizer.setPermissionResolver(permissionResolver);
        authorizer.setRolePermissionResolver(customRolePermissionResolver);
        return authorizer;
    }

    /**
     * 权限 解析器
     * <p>
     * 根据权限操作符, 创建不同的权限实例
     *
     * @return 权限 解析器
     */
    @Bean(name = "permissionResolver")
    public PermissionResolver permissionResolver() {
        return new WildcardPermissionResolver();
    }

    /**
     * 角色-权限 解析器
     * <p>
     * 根据角色名称获取对应的权限列表, 对相应的权限进行缓存管理
     *
     * @return 角色-权限 解析器
     */
    @Bean(name = "customRolePermissionResolver")
    public CustomRolePermissionResolver customRolePermissionResolver(PermissionResolver permissionResolver) {
        CustomRolePermissionResolver resolver = new CustomRolePermissionResolver();
        resolver.setPermissionResolver(permissionResolver);
        return resolver;
    }

    /**
     * EhCacheManager，缓存管理，用户登陆成功后，把用户信息和权限信息缓存起来，
     * 然后每次用户请求时，放入用户的session中，如果不设置这个bean，每个请求都会查询一次数据库。
     *
     * @return ehCacheManager
     */
    @Bean(name = "ehCacheManager")
    @DependsOn("lifecycleBeanPostProcessor")
    public EhCacheManager ehCacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
        return cacheManager;
    }

    /**
     * cookie管理对象;
     *
     * @return rememberMeManager
     */
    @Bean(name = "rememberMeManager")
    public CookieRememberMeManager rememberMeManager(SimpleCookie rememberMeCookie) {
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCookie(rememberMeCookie);
        return manager;
    }

    /**
     * rememberMe 是cookie的名称，对应前端的checkbox的name = rememberMe
     * 记住我cookie生效时间30天 ,单位秒
     *
     * @return rememberMeCookie
     */
    @Bean(name = "rememberMeCookie")
    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie();
        cookie.setName("rememberMe");
        cookie.setMaxAge(259200);
        return cookie;
    }

    /**
     * session管理
     *
     * @param scheduler session校验程序
     * @return sessionManager
     */
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager defaultWebSessionManager(ExecutorServiceSessionValidationScheduler scheduler) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(18000000);
        // url中是否显示session Id
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        // 删除失效的session
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionValidationInterval(18000000);
        sessionManager.setSessionValidationScheduler(scheduler);
        //设置SessionIdCookie 导致认证不成功，不从新设置新的cookie,从sessionManager获取sessionIdCookie
        //sessionManager.setSessionIdCookie(simpleIdCookie());
        sessionManager.getSessionIdCookie().setName("session-dudu-id");
        sessionManager.getSessionIdCookie().setPath("/");
        sessionManager.getSessionIdCookie().setMaxAge(60 * 60 * 24 * 7);
        return sessionManager;
    }

    /**
     * session校验程序
     *
     * @return executorServiceSessionValidationScheduler
     */
    @Bean(name = "executorServiceSessionValidationScheduler")
    public ExecutorServiceSessionValidationScheduler executorServiceSessionValidationScheduler() {
        ExecutorServiceSessionValidationScheduler scheduler = new ExecutorServiceSessionValidationScheduler();
        scheduler.setInterval(900000);
        return scheduler;
    }

    /**
     * 开启aop自动代理 与authorizationAttributeSourceAdvisor搭配使用
     *
     * @return defaultAdvisorAutoProxyCreator
     */
    @Bean(name = "defaultAdvisorAutoProxyCreator")
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    /**
     * shiro 注解提供类（aop）
     *
     * @return authorizationAttributeSourceAdvisor
     */
    @Bean(name = "authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }

    /**
     * 配置shiro在thymeleaf中使用的自定义tag
     *
     * @return shiroDialect
     */
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }


}
