package com.maxclay.dao;

import com.maxclay.model.User;

/**
 * @author maxclay
 */
public interface UserDao {

    User save(User user);

    User get(Long id);

    User getByEmail(String email);

}
