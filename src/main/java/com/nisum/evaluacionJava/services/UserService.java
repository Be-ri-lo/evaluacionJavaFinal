package com.nisum.evaluacionJava.services;

import com.nisum.evaluacionJava.entities.User;

public interface UserService {
    User saveUser(User user);

    User getUser(String email);

    User updated(Long id, User updatedUser);

    Boolean deleteUser(Long id, String email);

}
