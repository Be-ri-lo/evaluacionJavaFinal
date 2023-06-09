package com.nisum.evaluacionJava.services;

import com.nisum.evaluacionJava.dto.request.UserRequestDTO;
import com.nisum.evaluacionJava.dto.request.UserUpdateRequestDTO;
import com.nisum.evaluacionJava.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO saveUser(UserRequestDTO userRequestDTO);

    UserResponseDTO getUserEmail(String email);

    UserResponseDTO getUser(Long id);

    UserResponseDTO getUserToCreate(String email);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updated(String email, UserUpdateRequestDTO updatedUser);

    Boolean deleteUser(String email);

    Boolean verifyExistingUser(UserRequestDTO userRequestDTO);

    Boolean isEmailMatch(UserRequestDTO userRequestDTO);

    Boolean isPasswordMatch(UserRequestDTO userRequestDTO);


}
