package com.pandy.blog.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pandy.blog.common.Result;
import com.pandy.blog.common.ResultInfo;
import com.pandy.blog.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pandy
 * 处理登录成功
 * 返回角色信息和用户
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            MyUserDetails userDetails = (MyUserDetails) principal;
            User user = userDetails.getUser();
            List<String> roles = new ArrayList<>();
            for (GrantedAuthority role : userDetails.getAuthorities()) {
                roles.add(role.getAuthority());
            }
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(
                    objectMapper.writeValueAsString(Result.success()
                            .code(ResultInfo.SUCCESS.getCode())
                            .message(ResultInfo.SUCCESS.getMessage())
                            .data("user", user)));
        }
    }
}
