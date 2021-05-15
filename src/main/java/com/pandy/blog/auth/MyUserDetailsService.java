package com.pandy.blog.auth;

import com.pandy.blog.domain.User;
import com.pandy.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Pandy
 */
@Service
public class MyUserDetailsService  implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.getUserByName(username);

        MyUserDetails myUserDetails = new MyUserDetails();
        myUserDetails.setUser(user);
//        myUserDetails.setUsername("admin");
//        myUserDetails.setPassword("{noop}123456");
        myUserDetails.setUsername(user.getUsername());
        myUserDetails.setPassword("{noop}" + user.getPassword()); // noop表示未加密状态
        return myUserDetails;
    }
}
