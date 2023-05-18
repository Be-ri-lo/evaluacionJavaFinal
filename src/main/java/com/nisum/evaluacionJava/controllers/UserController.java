package com.nisum.evaluacionJava.controllers;

import com.nisum.evaluacionJava.entities.User;
import com.nisum.evaluacionJava.services.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {this.userService = userService; }

    //agregar mensajes en todas las respuestas.
    @PostMapping
    public ResponseEntity saveUser(@RequestBody User user) {
        return new ResponseEntity(userService.saveUser(user), HttpStatus.CREATED);
    }

    //prefiero buscarlo por mail
    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable("id") Long id) {
        return new ResponseEntity(userService.getUser(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updatedUser(@PathVariable("id") Long id, @RequestBody User user) {
        return new ResponseEntity(userService.updated(id, user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id, @PathVariable String email) {
        boolean answer = userService.deleteUser(id, email);
        if (answer == true) {
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
