package com.nisum.evaluacionJava.services;

import com.nisum.evaluacionJava.dto.request.PhoneRequestDTO;
import com.nisum.evaluacionJava.dto.response.PhoneResponseDTO;
import com.nisum.evaluacionJava.entities.Phone;
import com.nisum.evaluacionJava.entities.User;
import com.nisum.evaluacionJava.exceptions.CustomEx;
import com.nisum.evaluacionJava.repositories.PhoneRepository;
import com.nisum.evaluacionJava.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {

    private PhoneRepository phoneRepository;

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public PhoneServiceImpl(PhoneRepository phoneRepository,UserRepository userRepository, ModelMapper modelMapper) {
        this.phoneRepository = phoneRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PhoneResponseDTO savePhone(PhoneRequestDTO phoneRequestDTO) {
        try {
            //Optional<User> userOptional = userRepository.findById(phoneRequestDTO.getUserId());
            //if (userOptional.isEmpty()) {
               // throw new CustomEx("no hay usuario con este id", HttpStatus.INTERNAL_SERVER_ERROR);
           // }
            //User user = userOptional.get();
           // if (user.getPhones()!=null){
               // throw new CustomEx("ya hay un telefono", HttpStatus.INTERNAL_SERVER_ERROR);
            //}
            Phone phoneToSave = Phone.builder()
                    .phoneNumber(phoneRequestDTO.getPhoneNumber())
                    .cityCode(phoneRequestDTO.getCityCode())
                    .countryCode(phoneRequestDTO.getCountryCode())
                    //.usuario(user)
                    .build();



                    /*new Phone();
            phoneToSave.setPhoneNumber(phoneRequestDTO.getPhoneNumber());
            phoneToSave.setCityCode(phoneRequestDTO.getCityCode());
            phoneToSave.setCountryCode(phoneRequestDTO.getCountryCode());
            phoneToSave.setUsuario(user);*/
            phoneToSave = phoneRepository.save(phoneToSave);
            return PhoneResponseDTO.builder().id(phoneToSave.getId()).phoneNumber(phoneToSave.getPhoneNumber()).cityCode(phoneToSave.getCityCode()).countryCode(phoneToSave.getCountryCode()).build();
        } catch (CustomEx e) {
        throw new CustomEx("Error: Teléfono no se ha podido guardar", HttpStatus.INTERNAL_SERVER_ERROR);
    }
        /*try {
            Optional<Phone> phoneResponseDTO = getPhoneToCreate(phoneRequestDTO.getId());
            if (phoneResponseDTO.isEmpty()) {
                Phone phone = Phone.builder()
                        .id(phoneRequestDTO.getId())
                        .phoneNumber(phoneRequestDTO.getPhoneNumber())
                        .cityCode(phoneRequestDTO.getCityCode())
                        .countryCode(phoneRequestDTO.getCountryCode())
                        .build();
                phoneRepository.save(phone);

                return PhoneResponseDTO.builder()
                        .id(phone.getId())
                        .phoneNumber(phone.getPhoneNumber())
                        .cityCode(phone.getCityCode())
                        .countryCode(phone.getCountryCode())
                        .build();
            } else {
                throw new CustomEx("Error: Teléfono no se ha podido guardar", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (CustomEx e) {
            throw new CustomEx("Error: Teléfono no se ha podido guardar", HttpStatus.INTERNAL_SERVER_ERROR);
        }*/

    /*@Override
    public PhoneResponseDTO getPhone (Long id) {


        }
       // return phoneRepository.findById(id);
    }*/

    /*Optional<Phone> getPhoneToCreate(Long id) {
        try {
            return getPhone(id);
        } catch (CustomEx e) {
            return null;
        }
    }*/






}

    @Override
    public PhoneResponseDTO getPhone(Long id) {
        try {
            Optional<Phone> phone = phoneRepository.findById(id);
            if (phone.isEmpty()) {
                throw new CustomEx("No hay resultados para este id", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return modelMapper.map(phone.get(), PhoneResponseDTO.class);
        } catch (CustomEx e) {
            throw new CustomEx("prueba", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


