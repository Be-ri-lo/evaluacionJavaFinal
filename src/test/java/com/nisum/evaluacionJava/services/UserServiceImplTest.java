package com.nisum.evaluacionJava.services;

import com.nisum.evaluacionJava.controllers.UserController;
import com.nisum.evaluacionJava.dto.request.UserRequestDTO;
import com.nisum.evaluacionJava.dto.request.UserUpdateRequestDTO;
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
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.StreamSupport;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit tests of UserServiceImpl class")
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    private PhoneRepository phoneRepository;
    private ModelMapper modelMapper;
    private User userTest;

    private UserServiceImpl userServiceImpl;
    private UserResponseDTO userResponseDTO;
    private UserRequestDTO userRequestDTO;
    private JwtBuilderGeneratorService jwtBuilderGeneratorService;
    private User userUpdateTest;

    @BeforeEach
    public void beforeEachTest() {
        MockitoAnnotations.openMocks(this);

        userServiceImpl = new UserServiceImpl(userRepository, phoneRepository, modelMapper, jwtBuilderGeneratorService);
        userRepository = mock(UserRepository.class);
        modelMapper = new ModelMapper();
        jwtBuilderGeneratorService = new JwtBuilderGeneratorService();
        userUpdateTest = User.builder().id(1L).created(LocalDateTime.now()).updated(LocalDateTime.now()).lastLogin(LocalDateTime.now()).isActive(false).tokenId("fdjidsfjdsoifdsof").build();
        userResponseDTO = UserResponseDTO.builder().id(1L).created(LocalDateTime.now()).updated(LocalDateTime.now()).lastLogin(LocalDateTime.now()).isActive(false).tokenId("fdjidsfjdsoifdsof").build();
        userTest = User.builder().id(1L).name("Beatriz").email("Beatriz@gmail.com").phones(null).password("Checoslovaquia1!").build();
        userRequestDTO = UserRequestDTO.builder().name("Beatriz").email("Beatriz@gmail.com").phones(null).password("Checoslovaquia1!").build();
    }

    @Test
    @DisplayName("Save an User")
    public void saveUser() {
        when(userRepository.save(any())).thenReturn(userTest);
        UserServiceImpl userService = new UserServiceImpl(userRepository, null, modelMapper, jwtBuilderGeneratorService);
        UserResponseDTO userResponseDTO = userService.saveUser(userRequestDTO);

        assertNull(userResponseDTO.getId());
    }

    @Test
    @DisplayName("Crea un usuario con una Custom Exception")
    public void saveUser_whenCustomException() {
        CustomEx customEx = new CustomEx("Error: Usuario no ha podido ser guardado", HttpStatus.INTERNAL_SERVER_ERROR);
        CustomEx exception = assertThrows(CustomEx.class, () -> {
            UserServiceImpl userService = mock(UserServiceImpl.class);

            when(userService.saveUser(any())).thenThrow(customEx);
            UserController userController = new UserController(userService);

            userController.saveUser(any());
        });

        assertEquals(exception, customEx);
    }

    @Test
    @DisplayName("get User by email")
    void getUserEmail() {
        when(userRepository.findUserByEmail(anyString())).thenReturn(userTest);

        Optional<UserServiceImpl> userService = Optional.of(new UserServiceImpl(userRepository, phoneRepository, modelMapper, jwtBuilderGeneratorService));
        UserResponseDTO result = userService.get().getUserEmail("Beatriz@gmail.com");

        assertEquals(1, userResponseDTO.getId());
    }

    @Test
    @DisplayName("get User by email - User not found")
    public void getUserByEmail_UserNotFound() {
        String nonExistingEmail = "nonexisting@example.com";

        when(userRepository.findUserByEmail(nonExistingEmail)).thenReturn(null);

        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, null, modelMapper, jwtBuilderGeneratorService);

        UserResponseDTO result = userServiceImpl.getUserEmail(nonExistingEmail);

        assertNull(result);
        verify(userRepository).findUserByEmail(nonExistingEmail);
    }


    @Test
    @DisplayName("get User by id")
    public void getUserById() {
        when(userRepository.findById(any())).thenReturn(Optional.of(userTest));

        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, null, modelMapper, jwtBuilderGeneratorService);
        UserResponseDTO result = userServiceImpl.getUser(1L);

        assertEquals(result.getId(), userTest.getId());
        assertTrue(true, String.valueOf(userTest.getId() > 0));
        assertEquals("Beatriz", userTest.getName());
        assertEquals("Beatriz@gmail.com", userTest.getEmail());
    }

    @Test
    @DisplayName("get User by id - User not found")
    public void getUserById_UserNotFound() {
        long nonExistingUserId = 2L;
        Optional<User> nonExistingUserOptional = Optional.empty();
        when(userRepository.findById(nonExistingUserId)).thenReturn(nonExistingUserOptional);

        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, null, modelMapper, jwtBuilderGeneratorService);
        CustomEx exception = assertThrows(CustomEx.class, () -> {
            userServiceImpl.getUser(nonExistingUserId);
        });
        assertEquals("Error: Usuario no encontrado.", exception.getMessage());
    }

    @Test
    @DisplayName("Encuentra un usuario por id, pero hay una excepción")
    public void givenAnUser_whenThrowAnException() {
        Throwable exception = assertThrows(CustomEx.class, () -> {
            when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

            UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, null, modelMapper, jwtBuilderGeneratorService);
            userServiceImpl.getUser(1L);
        });

        assertTrue(exception.getMessage().contains("Error: Usuario no encontrado."));
    }


    @Test
    @DisplayName("Obtener usuario creado, encontrado")
    public void getUserToCreate_UserFound() {
        String email = "example@example.com";

        when(userRepository.findUserByEmail(any())).thenReturn(userTest);

        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, null, modelMapper, jwtBuilderGeneratorService);
        UserResponseDTO result = userServiceImpl.getUserEmail("Beatriz@gmail.com");

        assertTrue(true, String.valueOf(userTest.getId() > 0));
        assertEquals("Beatriz", userTest.getName());
        assertEquals("Beatriz@gmail.com", userTest.getEmail());

    }

    @Test
    @DisplayName("get User to create pero el usuario no fue encontrado")
    public void getUserToCreate_UserNotFound() {
        String email = "test@example.com";

        when(userRepository.findUserByEmail(email)).thenReturn(null);

        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, null, null, null);
        UserResponseDTO result = userServiceImpl.getUserToCreate(email);

        assertNull(result);
    }

    @Test
    @DisplayName("Get User to Create - Exception")
    public void testGetUserToCreate_Exception() {
        String email = "test@example.com";

        when(userRepository.findUserByEmail(email)).thenThrow(new RuntimeException("Error fetching user"));

        UserServiceImpl userService = new UserServiceImpl(userRepository, null, modelMapper, jwtBuilderGeneratorService);
        UserResponseDTO result = userService.getUserToCreate(email);

        assertNull(result);
    }

    @Test
    @DisplayName("Trae lista de todos los usuarios")
    public void getAllUsers()  {
        List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
        StreamSupport.stream(userRepository.findAll(). spliterator(), false)
                .map(element -> modelMapper.map(element, UserResponseDTO.class))
                .collect(Collectors.toList());

        when(userRepository.findAll()).thenReturn(getAllUserList(10));

        UserServiceImpl userServiceimpl = new UserServiceImpl(userRepository, null,modelMapper, jwtBuilderGeneratorService);
        List<UserResponseDTO> userAllResponseDTOList = userServiceimpl.getAllUsers();

        assertNotEquals(0, userAllResponseDTOList.size());
    }

    @Test
    @DisplayName("Obtener todos los usuarios, pero no se encuentran")
    public void getAllUsers_NoUsersFound() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, null, modelMapper, jwtBuilderGeneratorService);

        List<UserResponseDTO> result = userServiceImpl.getAllUsers();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(userRepository).findAll();
    }

    @Test
    @DisplayName("update User - Usuario encontrado")
    public void updateUser_UserFound() {
       String email = "user@example.com";
       UserUpdateRequestDTO updatedUser = new UserUpdateRequestDTO();

       updatedUser.setIsActive(false);
       LocalDateTime updatedDateTime = LocalDateTime.of(2023, 1,1,0,0);

       User foundUser = new User();
       foundUser.setId(1L);
       foundUser.setCreated(LocalDateTime.of(2023,1,1,0,0));
       foundUser.setUpdated(updatedDateTime);
       foundUser.setIsActive(true);
       foundUser.setTokenId("token123");

       when(userRepository.findUserByEmail(email)).thenReturn(foundUser);
       when(userRepository.save(foundUser)).thenReturn(foundUser);

       UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, null, modelMapper, jwtBuilderGeneratorService);

       UserResponseDTO result = userServiceImpl.updated(email, updatedUser);

       assertNotNull(result);
       assertEquals(foundUser.getId(), result.getId());
       assertEquals(foundUser.getCreated(), result.getCreated());
       assertFalse(result.getIsActive());
       assertEquals(foundUser.getTokenId(), result.getTokenId());
       verify(userRepository).findUserByEmail(email);
       verify(userRepository).save(foundUser);
    }

    @Test
    @DisplayName("update User - User no encontrado")
    public void updateUser_UserNotFound() {
        String email = "user@example.com";
        UserUpdateRequestDTO updatedUser = new UserUpdateRequestDTO();

        when(userRepository.findUserByEmail(email)).thenReturn(null);

        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, null, modelMapper, jwtBuilderGeneratorService);

        assertThrows(CustomEx.class, () -> userServiceImpl.updated(email, updatedUser));
        verify(userRepository).findUserByEmail(email);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    @DisplayName("Test updated - Internal server error")
    public void updated_InternalServerError() {
        String email = "test@example.com";
        UserUpdateRequestDTO updatedUser = new UserUpdateRequestDTO();

        User foundUser = new User();
        foundUser.setId(1L);
        foundUser.setCreated(LocalDateTime.of(2023, 1, 1, 0, 0));
        foundUser.setUpdated(LocalDateTime.of(2023, 1, 1, 0, 0));
        foundUser.setIsActive(true);
        foundUser.setTokenId("token123");

        when(userRepository.findUserByEmail(email)).thenReturn(foundUser);
        when(userRepository.save(foundUser)).thenThrow(new NullPointerException());

        UserServiceImpl userService = new UserServiceImpl(userRepository, null, modelMapper, jwtBuilderGeneratorService);

        assertThrows(CustomEx.class, () -> userService.updated(email, updatedUser));
    }

    @Test
    @DisplayName("Método update")
    public void updated_givenSavedUser() {
        when(userRepository.findUserByEmail(anyString())).thenReturn(userTest);
        when(userRepository.save(any())).thenReturn(userUpdateTest);

        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository,null, modelMapper, jwtBuilderGeneratorService);
        UserResponseDTO updateUser = userServiceImpl.updated("beatriz@gmail.com", UserUpdateRequestDTO.builder().build());

        assertEquals(false, updateUser.getIsActive());
    }

    @Test
    @DisplayName("Método delete")
    public void givenUser_and_deleteUser() {
        when(userRepository.findUserByEmail(anyString())).thenReturn(userTest);
        when(userRepository.save(userTest)).thenReturn(userTest);

        UserServiceImpl userService = new UserServiceImpl(userRepository, null,modelMapper, jwtBuilderGeneratorService);
        Boolean result = userService.deleteUser(userTest.getEmail());

        assert(result);
        assertFalse(userTest.getIsActive());
        assertNotNull(userTest.getUpdated());
    }

    @Test
    @DisplayName("Test deleteUser - usuario no encontrado")
    public void deleteUser_UserNotFound() {
        String email = "test@example.com";

        when(userRepository.findUserByEmail(email)).thenReturn(null);
        UserServiceImpl userService = new UserServiceImpl(userRepository, null, modelMapper, jwtBuilderGeneratorService);

        assertThrows(CustomEx.class, () -> userService.deleteUser(email));
    }

    @Test
    @DisplayName("Chequea que corresponda al formato de password")
    public void isPasswordMatch() {
        String password = "Valid@1234";
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setPassword(password);

        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, null, modelMapper, jwtBuilderGeneratorService);
        Boolean result = userServiceImpl.isPasswordMatch(userRequestDTO);

        assertTrue(result);
    }

    @Test
    @DisplayName("formato de password inválido")
    public void isPasswordMatch_InvalidPassword() {
        String password = "lala";
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setPassword(password);

        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, null, modelMapper, jwtBuilderGeneratorService);
        Boolean result = userServiceImpl.isPasswordMatch(userRequestDTO);

        assertFalse(result);

    }

    @Test
    @DisplayName("Chequea que el mail corresponda al formato")
    public void isEmailMatch() {
        String email = "valid@example.com";
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setEmail(email);

        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, null, modelMapper, jwtBuilderGeneratorService);
        Boolean result = userServiceImpl.isEmailMatch(userRequestDTO);

        assertTrue(result);
    }

    @Test
    @DisplayName("mail mal ingresado")
    public void isEmailMatch_InvalidEmail() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setEmail("invalid");

        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, null, modelMapper, jwtBuilderGeneratorService);
        Boolean result = userServiceImpl.isEmailMatch(userRequestDTO);

        assertFalse(result);
    }

    @Test
    @DisplayName("verifica si existe el mail y este no pueda repetirse")
    public void verifyExistingUser_ExistingUser() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setEmail("beatriz@mail.com");

       when(userRepository.findUserByEmail(userRequestDTO.getEmail())).thenReturn(userTest);

       UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, null, modelMapper, jwtBuilderGeneratorService);
       boolean result = userServiceImpl.verifyExistingUser(userRequestDTO);

        assertTrue(result);
        verify(userRepository).findUserByEmail(userRequestDTO.getEmail());
    }

    @Test
    @DisplayName("verifica que existe el mail, pero es nulo")
    public void verifyExistingUser_UserDoesNotExist() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setEmail("example@example.com");

        when(userRepository.findUserByEmail(userRequestDTO.getEmail())).thenReturn(null);

        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, null, modelMapper, jwtBuilderGeneratorService);
        Boolean result = userServiceImpl.verifyExistingUser(userRequestDTO);

        assertFalse(result);

        verify(userRepository).findUserByEmail(userRequestDTO.getEmail());
        verify(userRepository, times(1)).findUserByEmail("example@example.com");
    }

    private UserRequestDTO createUserRequestDTO() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setName("Bea");
        userRequestDTO.setEmail("example@example.com");
        userRequestDTO.setPassword("Password123!");
        userRequestDTO.setPhones(Collections.emptyList());
        return userRequestDTO;
    }

    private User createUser() {
        User user = new User();
        user.setId(1L);
        user.setCreated(LocalDateTime.now());
        user.setUpdated(LocalDateTime.now());
        user.setIsActive(true);
        user.setTokenId("token");
        return user;
    }

    public static User getSingleUser(Integer id){
        return User.builder()
                .id(1L)
                .name("beatriz")
                .email("beatriz@example.com")
                .password("121334")
                .phones(null)
                .build();
    }

    public static List<User> getAllUserList(Integer usersCount){
        return LongStream.rangeClosed(1, usersCount)
                .mapToObj(id -> getSingleUser((int) id))
                .collect(Collectors.toList());
    }

}


