package com.pandy.blog.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Pandy
 */
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        if (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE) ||
                request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {

            if (!request.getMethod().equals("POST")) {
                throw new AuthenticationServiceException("Authentication method not support:" + request.getMethod());
            }

            // 自动关闭流
            Map<String, String> map = new HashMap<>();
            ObjectMapper objectMapper = new ObjectMapper();

            try(ServletInputStream inputStream = request.getInputStream()) {
                map = objectMapper.readValue(inputStream, Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (map != null) {
                String username = map.get("username");
                System.out.println("username = " + username);
                String password = map.get("password");
                System.out.println("password = " + password);
                if (username == null) {
                    username = "";
                }

                if (password == null) {
                    password = "";
                }

                username.trim();
                password.trim();

                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
                // Allow subclasses to set the "details" property
                setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            }
            return null;
        }
        return null;
    }
}
