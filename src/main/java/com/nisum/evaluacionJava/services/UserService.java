package com.nisum.evaluacionJava.services;

import com.nisum.evaluacionJava.dto.request.UserRequestDTO;
import com.nisum.evaluacionJava.dto.request.UserUpdateRequestDTO;
import com.nisum.evaluacionJava.dto.response.UserResponseDTO;

public interface UserService {

    UserResponseDTO saveUser(UserRequestDTO userRequestDTO);

    UserResponseDTO getUserEmail(String email);

    UserResponseDTO getUser(Long id);

    UserResponseDTO updated(String email, UserUpdateRequestDTO updatedUser);

    Boolean deleteUser(String email);

}
