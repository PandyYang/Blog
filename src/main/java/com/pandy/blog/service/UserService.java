package com.pandy.blog.service;

import com.pandy.blog.domain.User;

/**
 * @author Pandy
 */
public interface UserService {
    User getUserByName(String name);
}
