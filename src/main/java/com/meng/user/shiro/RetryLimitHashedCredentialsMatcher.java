package com.meng.user.shiro;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mengli
 * @create 2019/12/23
 * @since 1.0.0
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private Ehcache passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher() {
        CacheManager cacheManager = CacheManager.newInstance(Objects.requireNonNull(CacheManager.class.getClassLoader().getResource("config/ehcache-shiro.xml")));
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        String username = String.valueOf(token.getPrincipal());

        Element element = passwordRetryCache.get(username);

        if (element == null) {
            element = new Element(username, new AtomicInteger(0));
            passwordRetryCache.put(element);
        }

        AtomicInteger retryCount = (AtomicInteger)element.getObjectValue();
        if(retryCount.incrementAndGet() > 5) {
            // if retry count > 5 throw
            throw new ExcessiveAttemptsException();
        }

        boolean matches = super.doCredentialsMatch(token, info);

        if(matches) {
            //clear retry count
            passwordRetryCache.remove(username);
        }
        return matches;
    }
}
