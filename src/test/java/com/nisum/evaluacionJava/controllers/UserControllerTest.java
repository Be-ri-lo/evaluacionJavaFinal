/*package com.nisum.evaluacionJava.controllers;

import com.nisum.evaluacionJava.dto.request.UserRequestDTO;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

   /* @Test
    @DisplayName("Test user getUserByEmail in a success scenario")
    public void getUserByEmail()  {
        String email = "beatriz@gmail.com";
        Mockito.when(mockedUserServiceImpl.getUserEmail(email)).thenReturn(UserResponseDTO.builder().id(1L).build());
        //ResponseEntity<UserResponseDTO> result = userController.getUserEmail(email);
        verify(mockedUserServiceImpl, times(1)).getUserEmail(email);

        assertEquals(UserResponseDTO.class, result.getBody().getClass());
        assertEquals(HttpStatus.OK, result.getStatusCode());

    }*/

/*

    @Test
    @DisplayName("Test updateUser in a successful scenario")
    public void updatedUser() {
        UserRequestDTO userRequestDTO = new UserRequestDTO("beatriz", "beatriz@nisum.com","12345",null);
        UserResponseDTO userResponseDto = UserResponseDTO.builder().id(1L).created(null).isActive(true).lastLogin(null).tokenId(null).updated(null).updated(null).build();
        Mockito.when(mockedUserServiceImpl.updated(1L, userRequestDTO)).thenReturn(userResponseDto);
        ResponseEntity<UserResponseDTO> response = userController.updatedUser( 1L, userRequestDTO);

        assertEquals(userResponseDto, response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(mockedUserServiceImpl, times(1)).updated(1L, userRequestDTO);
    }
*/

   /* @Test
    @DisplayName("Test deleteUser successful scenario")
    public void deleteUser() {
        UserServiceImpl userDelete = Mockito.mock(UserServiceImpl.class);
        Mockito.when(userDelete.deleteUser(any(), anyString())).thenReturn(true);
        UserController controller = new UserController(userDelete);
        HttpStatus response = controller.deleteUser(1L, "beatriz@gmail.com");

        assertEquals("200 OK", response.toString());
    }

    @Test
    @DisplayName("Test deleteUser successful scenario")
    public void deleteUserNotFound() {
        UserServiceImpl userDelete = Mockito.mock(UserServiceImpl.class);
        Mockito.when(userDelete.deleteUser(any(), anyString())).thenReturn(false);
        UserController controller = new UserController(userDelete);
        HttpStatus response = controller.deleteUser(2L, "beatriz@gmail.com");

        assertEquals("404 NOT_FOUND", response.toString());
    }


}


    */