package com.pandy.blog.auth;

import com.pandy.blog.domain.Role;
import com.pandy.blog.domain.User;
import com.pandy.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Pandy
 * 认证
 */
@Service
public class MyUserDetailsService  implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username == null) {
            throw new UsernameNotFoundException("用户名不能为空");
        }

        User user = userService.getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> roles =  user.getRoles();
//        List<String> roleNames = null;
//        for (Role role : roles) {
//            String res = role.getRoleName();
//            roleNames.add(res);
//        }

        SimpleGrantedAuthority simpleGrantedAuthority = null;
        for (Role role : roles) {
            simpleGrantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
            authorities.add(simpleGrantedAuthority);
        }

        UserAuth userAuth = new UserAuth();
        userAuth.setUser(user);
        userAuth.setAuthorities(authorities);
        return userAuth;


//        //用户角色
//        if (roles.size() > 0) {
//            MyUserDetails myUserDetails = new MyUserDetails();
//            myUserDetails.setUser(user);
////        myUserDetails.setUsername("admin");
////        myUserDetails.setPassword("{noop}123456");
//            myUserDetails.setUsername(user.getUsername());
//            myUserDetails.setPassword("{noop}" + user.getPassword()); // noop表示未加密状态
//
//            SimpleGrantedAuthority authority;
//            Set<GrantedAuthority> authorities = new HashSet<>();
//            for (Role role : roles) {
//                authority = new SimpleGrantedAuthority(role.getRoleName());
//                authorities.add(authority);
//            }
//            myUserDetails.setAuthorities(authorities);
//            UserAuth userAuth=new UserAuth();
//            userAuth.setUser(user);
//            userAuth.setAuthorities(authorities);
//            return userAuth;
//        } else {
//            throw  new UsernameNotFoundException("没有该用户");
//        }
    }
}
