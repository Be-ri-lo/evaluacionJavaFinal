package com.nisum.evaluacionJava.controllers;

import com.nisum.evaluacionJava.dto.request.UserRequestDTO;
import com.nisum.evaluacionJava.dto.request.UserUpdateRequestDTO;
import com.nisum.evaluacionJava.dto.response.UserResponseDTO;
import com.nisum.evaluacionJava.services.UserService;
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

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {this.userService = userService; }

    @PostMapping
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserRequestDTO user) {
        return new ResponseEntity(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity getUserEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserEmail(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{email}")
    public ResponseEntity updatedUser(@PathVariable("email") String email, @RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
        return new ResponseEntity(userService.updated(email, UserUpdateRequestDTO.builder().build()), HttpStatus.OK);
    }

    @DeleteMapping("/{email}")
    public HttpStatus deleteUser(@PathVariable String email) {
        boolean answer = userService.deleteUser(email);
        if (answer) {
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.NOT_FOUND;
        }
    }
}
