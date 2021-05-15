package com.pandy.blog.service.impl;

import com.pandy.blog.dao.UserDao;
import com.pandy.blog.domain.User;
import com.pandy.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Pandy
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByName(String name) {
        return userDao.getByUsername(name);
    }
}
