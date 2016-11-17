package com.maxclay.dao;

import com.maxclay.model.User;

import java.util.Optional;

/**
 * @author maxclay
 */
public interface UserDao {

    User save(User user);

    User get(Long id);

    Optional<User> getByEmail(String email);

}
