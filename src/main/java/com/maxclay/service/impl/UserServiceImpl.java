package com.maxclay.service.impl;

import com.maxclay.dao.UserDao;
import com.maxclay.dao.impl.UserDaoImpl;
import com.maxclay.exception.ResourceNotFoundException;
import com.maxclay.model.User;
import com.maxclay.service.UserService;

import java.util.Optional;

/**
 * @author maxclay
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User register(User user) {

        if (user == null) {
            throw new IllegalArgumentException("User cant be null");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("User's email cant be null");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("User's password cant be null");
        }

        if (userDao.getByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("User with such email already exists");
        }

        return userDao.save(user);
    }

    @Override
    public User get(Long id) {

        return userDao.get(id);
    }

    @Override
    public User getByEmail(String email) {

        Optional<User> userOptional = userDao.getByEmail(email);
        if(!userOptional.isPresent()) {
            throw new ResourceNotFoundException("User not found");
        }

        return userOptional.get();
    }
}
