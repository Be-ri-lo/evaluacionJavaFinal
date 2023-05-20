package com.nisum.evaluacionJava.services;

import com.nisum.evaluacionJava.dto.request.PhoneRequestDTO;
import com.nisum.evaluacionJava.dto.response.PhoneResponseDTO;
import com.nisum.evaluacionJava.entities.Phone;
import com.nisum.evaluacionJava.exceptions.CustomEx;
import com.nisum.evaluacionJava.repositories.PhoneRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {

    private PhoneRepository phoneRepository;
    private ModelMapper modelMapper;

    public PhoneServiceImpl(PhoneRepository phoneRepository, ModelMapper modelMapper) {
        this.phoneRepository = phoneRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PhoneResponseDTO savePhone(PhoneRequestDTO phoneRequestDTO) {
        try {
            Optional<Phone> phoneResponseDTO = getPhoneToCreate(phoneRequestDTO.getId());
            if (!phoneResponseDTO.isPresent()) {
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
        }
    }

    @Override
    public Optional<Phone> getPhone(Long id) {
        return phoneRepository.findById(id);
    }

    Optional<Phone> getPhoneToCreate(Long id) {
        try {
            return getPhone(id);
        } catch (CustomEx e) {
            return null;
        }
    }






}


