package com.nisum.evaluacionJava.services;

import com.nisum.evaluacionJava.entities.User;

public interface UserService {
    User saveUser(User user);

    Boolean verifyAndSave(User user);

    User getUser(Long id);

    User updated(Long id, User updatedUser);

    Boolean deleteUser(Long id, String email);

}
