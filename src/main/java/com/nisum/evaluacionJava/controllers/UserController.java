package com.nisum.evaluacionJava.controllers;

import com.nisum.evaluacionJava.dto.request.UserRequestDTO;
import com.nisum.evaluacionJava.dto.response.UserResponseDTO;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {this.userService = userService; }

    @PostMapping
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserRequestDTO user) {
        return new ResponseEntity(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity getUserEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserEmail(email));
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }*/

    @PutMapping("/{id}")
    public ResponseEntity updatedUser(@PathVariable("id") Long id, @RequestBody UserRequestDTO userRequestDTO) {
        return new ResponseEntity(userService.updated(id, userRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/{email}")
    public HttpStatus deleteUser(@PathVariable Long id, @PathVariable String email) {
        boolean answer = userService.deleteUser(id, email);
        if (answer == true) {
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.NOT_FOUND;
        }
    }
}
