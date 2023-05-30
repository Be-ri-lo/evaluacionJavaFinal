package com.nisum.evaluacionJava.services;

import com.nisum.evaluacionJava.dto.request.UserRequestDTO;
import com.nisum.evaluacionJava.dto.response.CreationObjectDTO;
import com.nisum.evaluacionJava.dto.response.UserResponseDTO;

public interface UserService {

    UserResponseDTO saveUser(UserRequestDTO userRequestDTO);
/*
    UserResponseDTO saveUser(UserRequestDTO userRequestDTO, Authentication authentication);
*/

    UserResponseDTO getUser(String email);

    UserResponseDTO updated(Long id, UserRequestDTO updatedUser);

    Boolean deleteUser(Long id, String email);

}
