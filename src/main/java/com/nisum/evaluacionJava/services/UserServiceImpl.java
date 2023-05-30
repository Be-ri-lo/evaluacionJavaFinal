package com.nisum.evaluacionJava.services;

import com.nisum.evaluacionJava.dto.request.UserRequestDTO;
import com.nisum.evaluacionJava.dto.response.UserResponseDTO;
import com.nisum.evaluacionJava.entities.Phone;
import com.nisum.evaluacionJava.entities.User;
import com.nisum.evaluacionJava.exceptions.CustomEx;
import com.nisum.evaluacionJava.repositories.PhoneRepository;
import com.nisum.evaluacionJava.repositories.UserRepository;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;
    private final ModelMapper modelMapper;


    public UserServiceImpl(UserRepository userRepository, PhoneRepository phoneRepository,  ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
        this.modelMapper = modelMapper;
    }

    private Boolean verifyExistingUser(UserRequestDTO userRequestDTO){
        UserResponseDTO foundUser = getUserEmail(userRequestDTO.getEmail());
        return foundUser != null;
    }

    private boolean isEmailMatch(UserRequestDTO userRequestDTO) {
        boolean emailMatch;
        String email = userRequestDTO.getEmail();
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        emailMatch = matcher.matches();
        return emailMatch;
    }

    private boolean isPasswordMatch(UserRequestDTO userRequestDTO){
        boolean passMatch;
        String pass = userRequestDTO.getPassword();
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,16}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(pass);
        passMatch = matcher.matches();
        return passMatch;

    }

    private UserResponseDTO getUserToCreate(String email) {
        try {
            return getUserEmail(email);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {
        try {
            if (verifyExistingUser(userRequestDTO)) {
                throw new CustomEx("Error: El correo ya fue registrado.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (!isEmailMatch(userRequestDTO)) {
                throw new CustomEx("Error: El correo no es válido.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (!isPasswordMatch(userRequestDTO)) {
                throw new CustomEx("Error: La contraseña no es válida.", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            UserResponseDTO userResponseDTO = getUserToCreate(userRequestDTO.getEmail());
            if (userResponseDTO == null) {

                LocalDateTime now = LocalDateTime.now();
                byte[] array = new byte[7];
                new Random().nextBytes(array);
                String randomString = new String(array, StandardCharsets.UTF_8);

                User user = User.builder()
                        .name(userRequestDTO.getName())
                        .email(userRequestDTO.getEmail())
                        .password(userRequestDTO.getPassword())
                        .isActive(true)
                        .phones((List<Phone>) userRequestDTO.getPhones())
                        .created(now)
                        .updated(now)
                        .tokenId(randomString)
                        .build();

                if(user.getPhones() != null && !user.getPhones().isEmpty()) {
                    for (Phone phone : user.getPhones()) {
                        phone.setUsuario(user);
                    }
                }

                User savedUser = userRepository.save(user);

                //metodo de hibernetes, voy inicializando las listas que quiero, y así evitar inicializar all automatico. Mejor LAZY que Eager.

                Hibernate.initialize(savedUser.getPhones());

                return UserResponseDTO
                        .builder()
                        .id(user.getId())
                        .created(user.getCreated())
                        .updated(user.getUpdated())
                        .lastLogin(user.getUpdated())
                        .isActive(user.getIsActive())
                        .tokenId(user.getTokenId())
                        .build();
            } else {
                throw new CustomEx("Error: Usuario no ha podido ser guardado", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (CustomEx e) {
            throw new Error(e);
        }
    }

    @Override
    public UserResponseDTO getUserEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if(user == null) return null;
        return modelMapper.map(user, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public UserResponseDTO updated(Long id, UserRequestDTO updatedUser) {
        try {
            User foundUser = userRepository.findById(id).get();
           // foundUser.setId(updatedUser.getPhones().getId());

            userRepository.save(foundUser);

            return UserResponseDTO
                    .builder()
                    .id(foundUser.getId())
                    .created(foundUser.getCreated())
                    .updated(foundUser.getUpdated())
                    .lastLogin(foundUser.getUpdated())
                    .isActive(foundUser.getIsActive())
                    .tokenId(foundUser.getTokenId())
                    .build();

        } catch (CustomEx e) {
            throw new CustomEx("Error: No ha sido posible actualizar", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean deleteUser(Long id, String email) {
        try{
            User user = userRepository.findUserByIdAndEmail(id, email);
            user.setIsActive(false);
            user.setUpdated(LocalDateTime.now());
            userRepository.save(user);
            return true;
        } catch(CustomEx e) {
            throw new CustomEx("Error: Usuario no existe", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

