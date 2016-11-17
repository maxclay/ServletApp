package com.maxclay.service;

import com.maxclay.model.User;

/**
 * @author maxclay
 */
public interface UserService {

    User register(User user);

    User get(Long id);

    User getByEmail(String email);

}
