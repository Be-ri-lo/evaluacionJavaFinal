package com.nisum.evaluacionJava.repositories;

import com.nisum.evaluacionJava.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByIdAndEmail(Long id, String email);

    User findUserByEmail(String email);

}
