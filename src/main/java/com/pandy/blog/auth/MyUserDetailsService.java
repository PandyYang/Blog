package com.pandy.blog.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Pandy
 */
@Service
public class MyUserDetailsService  implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        MyUserDetails myUserDetails = new MyUserDetails();
        myUserDetails.setUsername("admin");
        myUserDetails.setPassword("{noop}123456");
        return myUserDetails;
    }
}
