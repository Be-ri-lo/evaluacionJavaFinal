package com.nisum.evaluacionJava.controllers;

import com.nisum.evaluacionJava.dto.request.UserRequestDTO;
import com.nisum.evaluacionJava.dto.request.UserUpdateRequestDTO;
import com.nisum.evaluacionJava.dto.response.UserResponseDTO;
import com.nisum.evaluacionJava.services.UserService;
import com.nisum.evaluacionJava.services.UserServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    private UserService userService;
    private UserServiceImpl userServiceImpl;
    private UserUpdateRequestDTO userUpdateRequestDTO;

    @Mock
    UserServiceImpl mockedUserServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = Mockito.mock(UserService.class);

    }
    @Test
    @DisplayName("Test saveUser in a success scenario")
    public void saveUser() {
        UserRequestDTO userRequestDTO = new UserRequestDTO("Beatriz", "beatriz@gmail.com", "12345", null);
        UserResponseDTO userResponseDto = UserResponseDTO.builder().id(1L).created(null).isActive(true).lastLogin(null).tokenId(null).updated(null).updated(null).build();
        when(mockedUserServiceImpl.saveUser(userRequestDTO)).thenReturn(userResponseDto);
        ResponseEntity<UserResponseDTO> response = userController.saveUser(userRequestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @DisplayName("Test user getUser in a success scenario")
    public void getUserById()  {
        Long idUser = 1L;
        when(mockedUserServiceImpl.getUser(idUser)).thenReturn(UserResponseDTO.builder().id(1L).build());
        ResponseEntity<UserResponseDTO> result = userController.getUser(idUser);
        verify(mockedUserServiceImpl, times(1)).getUser(idUser);

        assertEquals(UserResponseDTO.class, result.getBody().getClass());
        assertEquals(HttpStatus.OK, result.getStatusCode());

    }

    @Test
    @DisplayName("Test user getUserByEmail in a success scenario")
    public void getUserByEmail()  {
        String email = "beatriz@gmail.com";
        when(mockedUserServiceImpl.getUserEmail(email)).thenReturn(UserResponseDTO.builder().id(1L).build());
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

        when(mockedUserServiceImpl.getAllUsers()).thenReturn(userDTOS);
        ResponseEntity<List<UserResponseDTO>> response = userController.getAllUsers();

        verify(mockedUserServiceImpl, times(1)).getAllUsers();
        assertEquals(ResponseEntity.ok(mockedUserServiceImpl.getAllUsers()), userController.getAllUsers());
        assertEquals(userDTOS, response.getBody());
        assertEquals("<200 OK OK,[UserResponseDTO(id=1, created=null, updated=null, lastLogin=null, isActive=null, tokenId=null)],[]>", response.toString());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    @DisplayName("Actualización de usuario en escenario exitoso")
    public void testUpdatedUser_Successful() {
        String email = "bea@example.com";
        UserUpdateRequestDTO userUpdateRequestDTO = new UserUpdateRequestDTO();

        UserResponseDTO updatedUserResponse = new UserResponseDTO();
        doReturn(updatedUserResponse).when(mockedUserServiceImpl).updated(eq(email), any(UserUpdateRequestDTO.class));

        ResponseEntity responseEntity = userController.updatedUser(email, userUpdateRequestDTO);

        assertNotNull(responseEntity);
        verify(mockedUserServiceImpl).updated(eq(email), any(UserUpdateRequestDTO.class));
    }

    @Test
    @DisplayName("Delete en escenario exitoso")
    public void testDeleteUser_Successful() {
        String email = "lala@example.com";

        when(mockedUserServiceImpl.deleteUser(email)).thenReturn(true);
        HttpStatus result = userController.deleteUser(email);


        assertEquals(HttpStatus.OK, result);
        verify(mockedUserServiceImpl).deleteUser(email);
    }

    @Test
    @DisplayName("Test deleteUser cuando no se encuentra usuario")
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
        when(mockedUserServiceImpl.deleteUser(email)).thenReturn(false);
        HttpStatus response = userController.deleteUser(email);

        verify(mockedUserServiceImpl).deleteUser(email);
        assertEquals("404 NOT_FOUND", response.toString());assertEquals("404 NOT_FOUND", response.toString());
        assertNull(userController.getUserEmail(email).getBody());
    }

    @Test
    @DisplayName("No existe usuario")
    public void testDeleteUser_NotFound() {
        String email = "test@example.com";
        when(mockedUserServiceImpl.deleteUser(email)).thenReturn(false);

        HttpStatus result = userController.deleteUser(email);
        assertEquals(HttpStatus.NOT_FOUND, result);
    }
}
