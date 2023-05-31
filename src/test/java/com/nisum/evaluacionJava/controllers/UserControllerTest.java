package com.nisum.evaluacionJava.controllers;

import com.nisum.evaluacionJava.dto.request.UserRequestDTO;
import com.nisum.evaluacionJava.dto.request.UserUpdateRequestDTO;
import com.nisum.evaluacionJava.dto.response.UserResponseDTO;
import com.nisum.evaluacionJava.services.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    UserServiceImpl mockedUserServiceImpl;

    @Test
    @DisplayName("Test saveUser in a success scenario")
    public void saveUser() {
        UserRequestDTO userRequestDTO = new UserRequestDTO("Beatriz", "beatriz@gmail.com", "12345", null);
        UserResponseDTO userResponseDto = UserResponseDTO.builder().id(1L).created(null).isActive(true).lastLogin(null).tokenId(null).updated(null).updated(null).build();
        Mockito.when(mockedUserServiceImpl.saveUser(userRequestDTO)).thenReturn(userResponseDto);
        ResponseEntity<UserResponseDTO> response = userController.saveUser(userRequestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @DisplayName("Test user getUser in a success scenario")
    public void getUserById()  {
        Long idUser = 1L;
        Mockito.when(mockedUserServiceImpl.getUser(idUser)).thenReturn(UserResponseDTO.builder().id(1L).build());
        ResponseEntity<UserResponseDTO> result = userController.getUser(idUser);
        verify(mockedUserServiceImpl, times(1)).getUser(idUser);

        assertEquals(UserResponseDTO.class, result.getBody().getClass());
        assertEquals(HttpStatus.OK, result.getStatusCode());

    }

    @Test
    @DisplayName("Test user getUserByEmail in a success scenario")
    public void getUserByEmail()  {
        String email = "beatriz@gmail.com";
        Mockito.when(mockedUserServiceImpl.getUserEmail(email)).thenReturn(UserResponseDTO.builder().id(1L).build());
        ResponseEntity<UserResponseDTO> result = userController.getUserEmail(email);
        verify(mockedUserServiceImpl, times(1)).getUserEmail(email);

        assertEquals(UserResponseDTO.class, result.getBody().getClass());
        assertEquals(HttpStatus.OK, result.getStatusCode());

    }

    @Test
    @DisplayName("Test user getAllUser in a success scenario")
    public void getAllUsers()  {
        List<UserResponseDTO> userDTOS = new ArrayList<>();
        userDTOS.add(UserResponseDTO.builder().id(1L).build());
        Mockito.when(mockedUserServiceImpl.getAllUsers()).thenReturn(userDTOS);
        ResponseEntity<List<UserResponseDTO>> response = userController.getAllUsers();
        verify(mockedUserServiceImpl, times(1)).getAllUsers();
        assertEquals(ResponseEntity.ok(mockedUserServiceImpl.getAllUsers()), userController.getAllUsers());
        assertEquals(userDTOS, response.getBody());
        assertEquals("<200 OK OK,[UserResponseDTO(id=1, created=null, updated=null, lastLogin=null, isActive=null, tokenId=null)],[]>", response.toString());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

   /* @Test
    @DisplayName("Test updateUser in a successful scenario")
    public void updatedUser() {
        UserUpdateRequestDTO userUpdateRequestDTO = new UserUpdateRequestDTO("beatriz", "beatriz@nisum.com","12345",true, null);
        UserResponseDTO userResponseDto = UserResponseDTO.builder().id(1L).created(null).isActive(true).lastLogin(null).tokenId(null).updated(null).updated(null).build();
        Mockito.when(mockedUserServiceImpl.updated("beatriz@nisum.com",userUpdateRequestDTO)).thenReturn(userResponseDto);
        ResponseEntity<UserResponseDTO> response = userController.updatedUser( "beatriz@nisum.con", userUpdateRequestDTO);

        assertEquals(userResponseDto, response.getBody());
        //assertEquals(1L, response.getBody().getId());
       // assertEquals(HttpStatus.OK, response.getStatusCode());
       // verify(mockedUserServiceImpl, times(1)).updated("beatriz@nisum.con", userUpdateRequestDTO);
    }*/

    @Test
    @DisplayName("Test deleteUser successful scenario")
    public void deleteUser() {
        String email= "beatriz@nisum.com";
        UserRequestDTO userRequestDTO = UserRequestDTO.builder().name("Beatriz").email(email).password("1224325").phones(null).build();
        UserResponseDTO userToDelete = UserResponseDTO.builder()
                .id(1L)
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .tokenId("232325")
                .isActive(false)
                .build();
        Mockito.when(mockedUserServiceImpl.deleteUser(email)).thenReturn(false);
        HttpStatus response = userController.deleteUser(email);
        verify(mockedUserServiceImpl).deleteUser(email);
        assertEquals("404 NOT_FOUND", response.toString());assertEquals("404 NOT_FOUND", response.toString());
        assertNull(userController.getUserEmail(email).getBody());
    }

}
