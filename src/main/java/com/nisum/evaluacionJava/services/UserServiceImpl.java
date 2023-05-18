package com.nisum.evaluacionJava.services;

import com.nisum.evaluacionJava.entities.User;
import com.nisum.evaluacionJava.exceptions.CustomEx;
import com.nisum.evaluacionJava.repositories.UserRepository;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    //metodo para verificar si mail existe, de lo contrario crear usuario, si existe en bd, arrojar mensaje de error, el correo ya existe.....
    /*@Override
    public Boolean verifyAndSave(User user){
        try{
            getUser(user.getId());
            return true;
        } catch (Exception e) {
            try {
                if (user.getEmail().equals(user.getEmail())) {
                    User userVerify = User.builder()
                            .name(user.getName())
                            .email(user.getEmail())
                            .password(user.getPassword())
                            .createdAt(LocalDateTime.now())
                            .active(true)
                            .build();
                    saveUser(userVerify);
                    return false;
                }
                throw new Exception();
            } catch (Exception e) {
                throw new CustomEx("El correo ya fue registrado.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }*/

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
                throw new RuntimeException();
            }
        );
    }

    @Override
    public User updated(Long id, User updatedUser) {
        User searchUser = userRepository.findById(id).get();
        searchUser.setEmail(updatedUser.getEmail());
        return userRepository.save(searchUser);
    }

    @Override
    public boolean deleteUser(Long id) {
        try{
            userRepository.deleteById(id);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
