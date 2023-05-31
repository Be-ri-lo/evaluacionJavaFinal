/*package com.nisum.evaluacionJava.services;

import com.nisum.evaluacionJava.controllers.UserController;
import com.nisum.evaluacionJava.dto.request.UserRequestDTO;
import com.nisum.evaluacionJava.dto.response.UserResponseDTO;
import com.nisum.evaluacionJava.entities.User;
import com.nisum.evaluacionJava.exceptions.CustomEx;
import com.nisum.evaluacionJava.repositories.PhoneRepository;
import com.nisum.evaluacionJava.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;*/

/*
@ExtendWith(MockitoExtension.class)
@DisplayName("Unit tests of UserServiceImpl class")
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    private PhoneRepository phoneRepository;
    private ModelMapper modelMapper;
    private User userTest;
    private UserResponseDTO userResponseDTO;
    private UserRequestDTO userRequestDTO;

    @BeforeEach
    public void beforeEachTest() {
        userTest = User.builder().id(1L).name("Beatriz").email("Beatriz@gmail.com").phones(null).password("Checoslovaquia1!").build();
        userRequestDTO = UserRequestDTO.builder().name("Beatriz").email("Beatriz@gmail.com").phones(null).password("Checoslovaquia1!").build();
    }

    @Test
    @DisplayName("Save an User")
    void saveUser() {
        when(userRepository.save(any())).thenReturn(userTest);
        UserServiceImpl userService = new UserServiceImpl(userRepository, null, modelMapper);
        UserResponseDTO userResponseDTO = userService.saveUser(userRequestDTO);

        assertEquals(null, userResponseDTO.getId());
    }

    @Test
    @DisplayName("Save an User but exist an Custom Exception")
    public void saveUserCustomException() {
        CustomEx customEx = new CustomEx("Error: Usuario no ha podido ser guardado", HttpStatus.INTERNAL_SERVER_ERROR);
        CustomEx exception = assertThrows(CustomEx.class, () -> {
            UserServiceImpl userService = Mockito.mock(UserServiceImpl.class);

            when(userService.saveUser(any())).thenThrow(customEx);
            UserController userController = new UserController(userService);

            userController.saveUser(any());
        });

        assertEquals(exception, customEx);
    }

   /* @Test
    void getUserEmail() {
        when(userRepository.findUserByEmail(anyString())).thenReturn(userTest);

        Optional<UserServiceImpl> userService = Optional.of(new UserServiceImpl(userRepository, phoneRepository, modelMapper));
        UserResponseDTO result = userService.get().getUserEmail("Beatriz@gmail.com");

        //assertEquals(result.getId(), userTest.getId());
        assertEquals(null, userResponseDTO.getId());
    }*/

/*
    @Test
    @DisplayName("get User by id")
    public void getUserById() {
        when(userRepository.findById(any())).thenReturn(Optional.of(userTest));

        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, null, null);
        UserResponseDTO result = userServiceImpl.getUser(1L);

        assertEquals(result.getId(), userTest.getId());
        assertTrue(true, String.valueOf(userTest.getId() > 0));
        assertEquals("Beatriz", userTest.getName());
        assertEquals("Beatriz@gmail.com", userTest.getEmail());
    }

  /*  @Test
    @DisplayName("get User to create")
    public void getUserToCreate() {
        when(userRepository.findUserByEmail(any())).thenReturn(userTest);

        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, phoneRepository, modelMapper);
        UserResponseDTO result = userServiceImpl.getUserEmail("Beatriz@gmail.com");

        assertTrue(true, String.valueOf(userTest.getId() > 0));
        assertEquals("Beatriz", userTest.getName());
        assertEquals("Beatriz@gmail.com", userTest.getEmail());
    }*/
/*
    @Test
    @DisplayName("Test user getUserByEmail in a success scenario")
    public void getAllUsers()  {
            List<User> userList = new ArrayList<>();

            }*/
/*
    @Test
    void updated() {
    }

   /* @Test
    public void deleteUser() {
        when(userRepository.findUserByIdAndEmail(any(),anyString())).thenReturn(userTest);

        UserServiceImpl userService = new UserServiceImpl(userRepository, null,null);
        Boolean result = userService.deleteUser(userTest.getId(), userTest.getEmail());

        assert(result);
    }*/

/*
    @Test
    public void isPasswordMatch() {
    }

    @Test
    public void isEmailMatch() {
    }

    @Test
    public void verifyExistingUser() {
    }
}*/