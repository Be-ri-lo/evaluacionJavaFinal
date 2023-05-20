package com.nisum.evaluacionJava.services;

import com.nisum.evaluacionJava.dto.request.UserRequestDTO;
import com.nisum.evaluacionJava.dto.response.UserResponseDTO;

public interface UserService {

    UserResponseDTO saveUser(UserRequestDTO userRequestDTO);

    UserResponseDTO getUserEmail(String email);

    UserResponseDTO getUser(Long id);

    UserResponseDTO updated(Long id, UserRequestDTO updatedUser);

    Boolean deleteUser(Long id, String email);

}
