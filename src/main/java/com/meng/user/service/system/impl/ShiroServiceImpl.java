package com.meng.user.service.system.impl;

import com.meng.user.service.system.PermissionService;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 大橙子
 * @create 2019/8/8
 * @since 1.0.0
 *
 * 在数据中查询所有的资源，将该资源的url当作key，配置拥有该url权限的用户才可访问该url。
 * 最后加入 filterChainDefinitionMap.put(“/*”, “authc”);表示其他没有配置的链接都需要认证才可访问。注意这个要放最后面，因为shiro的匹配是从上往下，如果匹配到就不继续匹配了，所以把 /放到最前面，则 后面的链接都无法匹配到了。
 * 而这段代码是在项目启动的时候加载的。加载的数据是放到内存中的。但是当权限增加或者删除时，正常情况下不会重新启动来，重新加载权限。所以需要调用以下代码的updatePermission()方法来重新加载权限。其实下面的代码有些重复了，可以稍微调整下，我就先这么写了。
 */
@Service
public class ShiroServiceImpl {

    private final ShiroFilterFactoryBean shiroFilterFactoryBean;
    private final PermissionService permissionService;

    @Autowired
    public ShiroServiceImpl(PermissionService permissionService, ShiroFilterFactoryBean shiroFilterFactoryBean) {
        this.permissionService = permissionService;
        this.shiroFilterFactoryBean = shiroFilterFactoryBean;
    }

    // @Autowired
    // private RedisSessionDAO redisSessionDAO;

    /**
     * 初始化权限
     */
    public Map<String, String> loadFilterChainDefinitions() {
        // 权限控制map.从数据库获取
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/font-awesome/**", "anon");
        // List<Resources> resourcesList = resourceService.queryAll();
        // for(Resources resources:resourcesList){
        //
        //     if (StringUtil.isNotEmpty(resources.getResurl())) {
        //         String permission = "perms[" + resources.getResurl()+ "]";
        //         filterChainDefinitionMap.put(resources.getResurl(),permission);
        //     }
        // }
        // filterChainDefinitionMap.put("/**", "authc");
        return filterChainDefinitionMap;
    }

    /**
     * 重新加载权限
     */
    public void updatePermission() {

        synchronized (shiroFilterFactoryBean) {

            AbstractShiroFilter shiroFilter;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            } catch (Exception e) {
                throw new RuntimeException(
                        "get ShiroFilter from shiroFilterFactoryBean error!");
            }

            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();

            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

            // 清空老的权限控制
            manager.getFilterChains().clear();

            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());

            // 重新构建生成
            Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();

            for (Map.Entry<String, String> entry : chains.entrySet()) {

                String url = entry.getKey();

                String chainDefinition = entry.getValue()
                        .trim()
                        .replace(" ", "");

                manager.createChain(url, chainDefinition);
            }

            System.out.println("更新权限成功！！");
        }
    }
}
