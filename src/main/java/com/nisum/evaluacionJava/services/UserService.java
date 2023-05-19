package com.nisum.evaluacionJava.services;

import com.nisum.evaluacionJava.dto.request.UserRequestDTO;
import com.nisum.evaluacionJava.dto.response.UserResponseDTO;

public interface UserService {

    UserResponseDTO saveUser(UserRequestDTO userRequestDTO);

    UserResponseDTO getUser(String email);

    UserResponseDTO updated(Long id, UserRequestDTO updatedUser);

    Boolean deleteUser(Long id, String email);

}
