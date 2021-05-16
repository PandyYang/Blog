package com.pandy.blog.auth;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author Pandy
 */
@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final boolean supports = true;

    /**
     * 返回被拦截路径需要的权限信息
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        final String uri = request.getRequestURI();
        final String method = request.getMethod();
        AntPathMatcher matcher = new AntPathMatcher();
        // 先匹配路径 再匹配方法
        boolean b1 = matcher.match("/**", uri);
        //boolean b2 = method.toUpperCase().equals("");
        //if (b1 && b2) {
            return SecurityConfig.createList("admin");
        //}
        //return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return supports;
    }
}
