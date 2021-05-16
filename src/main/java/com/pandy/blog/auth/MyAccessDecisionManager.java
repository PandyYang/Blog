package com.pandy.blog.auth;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author Pandy
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        // 如果没有需要的权限信息值 放行
        if(collection == null) {
            return;
        }

        if (!authentication.isAuthenticated()) {
            throw  new AccessDeniedException("权限不足！");
        }

        Collection<? extends GrantedAuthority> collection1 = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : collection1) {
            // 用户的角色信息
            final String authority = grantedAuthority.getAuthority();
            // 再获取需要的权限信息
            for (ConfigAttribute configAttribute: collection) {
                if (authority.equals(configAttribute.getAttribute())) {
                    return;
                }
            }
        }
        throw  new AccessDeniedException("权限不足！");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
