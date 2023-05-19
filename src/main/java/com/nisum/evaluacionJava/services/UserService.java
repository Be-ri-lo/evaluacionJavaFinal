package com.nisum.evaluacionJava.services;

import com.nisum.evaluacionJava.dto.request.UserRequestDTO;
import com.nisum.evaluacionJava.dto.response.CreationObjectDTO;
import com.nisum.evaluacionJava.dto.response.UserResponseDTO;
import com.nisum.evaluacionJava.entities.User;

public interface UserService {

    CreationObjectDTO saveUser(UserRequestDTO userRequestDTO);

    UserResponseDTO getUser(String email);

    CreationObjectDTO updated(Long id, UserRequestDTO updatedUser);

    Boolean deleteUser(Long id, String email);

}
