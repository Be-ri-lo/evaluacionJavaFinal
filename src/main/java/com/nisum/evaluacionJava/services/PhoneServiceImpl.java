package com.nisum.evaluacionJava.services;

import com.nisum.evaluacionJava.dto.request.PhoneRequestDTO;
import com.nisum.evaluacionJava.dto.response.PhoneResponseDTO;
import com.nisum.evaluacionJava.entities.Phone;
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
            Phone phoneToSave = Phone.builder()
                    .phoneNumber(phoneRequestDTO.getPhoneNumber())
                    .cityCode(phoneRequestDTO.getCityCode())
                    .countryCode(phoneRequestDTO.getCountryCode())
                    .build();
            phoneToSave = phoneRepository.save(phoneToSave);

            return PhoneResponseDTO
                    .builder()
                    .id(phoneToSave.getId())
                    .phoneNumber(phoneToSave.getPhoneNumber())
                    .cityCode(phoneToSave.getCityCode())
                    .countryCode(phoneToSave.getCountryCode())
                    .build();

        } catch (CustomEx e) {
            throw new CustomEx("Error: Teléfono no se ha podido guardar", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public PhoneResponseDTO getPhone(Long id) {
            Optional<Phone> phone = phoneRepository.findById(id);
            return modelMapper.map(phone, PhoneResponseDTO.class);
    }


}


