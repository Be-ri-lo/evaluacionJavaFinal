package com.nisum.evaluacionJava.services;

import com.nisum.evaluacionJava.dto.request.UserRequestDTO;
import com.nisum.evaluacionJava.dto.response.CreationObjectDTO;
import com.nisum.evaluacionJava.dto.response.UserResponseDTO;
import com.nisum.evaluacionJava.entities.Phone;
import com.nisum.evaluacionJava.entities.User;
import com.nisum.evaluacionJava.exceptions.CustomEx;
import com.nisum.evaluacionJava.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private ModelMapper modelMapper;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    //metodo para verificar si mail existe, de lo contrario crear usuario, si existe en bd, arrojar mensaje de error, el correo ya existe.....

    private Boolean verifyExistingUser(UserRequestDTO userRequestDTO){
        UserResponseDTO foundUser = getUser(userRequestDTO.getEmail());
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
            return getUser(email);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CreationObjectDTO saveUser(UserRequestDTO userRequestDTO) {
        try {
            if (verifyExistingUser(userRequestDTO)) {
                throw new CustomEx(String.format("Error: El correo ya fue registrado."), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (!isEmailMatch(userRequestDTO)) {
                throw new CustomEx("El correo no es válido.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (!isPasswordMatch(userRequestDTO)) {
                throw new CustomEx("La contraseña no es válida.", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            UserResponseDTO userResponseDTO = getUserToCreate(userRequestDTO.getEmail());
            if (userResponseDTO == null) {
                User user = User.builder()
                        .name(userRequestDTO.getName())
                        .email(userRequestDTO.getEmail())
                        .password(userRequestDTO.getPassword())
                        .isActive(userRequestDTO.getActive())
                        .phone((List<Phone>) userRequestDTO.getPhoneId())
                        .created(userRequestDTO.getDayOfLogin())
                        .updated(userRequestDTO.getDayOfLogin())
                        .build();
                User userSave =  userRepository.save(user);
                return CreationObjectDTO.builder()
                        .id(userSave.getId())
                        .uri("/user/" + userSave.getId())
                        .build();
            } else {
                throw new CustomEx("No ha podido ser guardado", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (CustomEx e) {
            throw new Error(e);
        }
    }

    @Override
    public UserResponseDTO getUser(String email) {
        User user = userRepository.findUserByEmail(email);
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public CreationObjectDTO updated(Long id, UserRequestDTO updatedUser) {
        try {
            User searchUser = userRepository.findById(id).get();
            searchUser.setEmail(updatedUser.getEmail());

            User userSave = userRepository.save(searchUser);
            return CreationObjectDTO.builder()
                    .id(userSave.getId()).uri("/user/" + userSave.getId())
                    .build();
        } catch (Exception e) {
            throw new Error(String.format("Error: %s", e.getMessage()));
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
        } catch(Exception e) {
            throw new CustomEx(String.format("Error: $s", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
