package com.nisum.evaluacionJava.services;

import com.nisum.evaluacionJava.entities.User;
import com.nisum.evaluacionJava.exceptions.CustomEx;
import com.nisum.evaluacionJava.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //metodo para verificar si mail existe, de lo contrario crear usuario, si existe en bd, arrojar mensaje de error, el correo ya existe.....

    private Boolean verifyExistingUser(User user){
        User foundUser = getUser(user.getEmail());
        return foundUser != null;
    }

    private boolean isEmailMatch(User user) {
        boolean emailMatch;
        String email = user.getEmail();
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        emailMatch = matcher.matches();
        return emailMatch;
    }
    @Override
    public User saveUser(User user) {
        if (verifyExistingUser(user)) {
            //si existe -> comprobación del mail  -> lanzo excepción
            throw new CustomEx("El correo ya fue registrado.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // chequear que el correo esté con regex.
        if (!isEmailMatch(user)) {
            throw new CustomEx("El correo no es válido.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //creo usuario
        return userRepository.save(user);
    }

    @Override
    public User getUser(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User updated(Long id, User updatedUser) {
        User searchUser = userRepository.findById(id).get();
        searchUser.setEmail(updatedUser.getEmail());
        return userRepository.save(searchUser);
    }

    @Override
    public Boolean deleteUser(Long id, String email) {
        try{
            User user = userRepository.findUserByIdAndEmail(id, email);
            user.setActive(false);
            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);
            return true;
        } catch(Exception e) {
            throw new CustomEx(String.format("Error: $s", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public User getAllUser() {
        return (User) userRepository.findAll();
    }
}
