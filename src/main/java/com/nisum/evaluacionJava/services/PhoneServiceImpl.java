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
            PhoneResponseDTO phoneResponseDTO = getPhoneToCreate(phoneRequestDTO.getId());
            if (phoneResponseDTO == null) {
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
                throw new CustomEx("Error: Teléfono no ha podido ser guardado", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (CustomEx e) {
            throw new CustomEx("Error: Teléfono no ha podido ser guardado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public PhoneResponseDTO getPhone(Long id) {
        Optional<Phone> phone = phoneRepository.findById(id);
        return modelMapper.map(phone, PhoneResponseDTO.class);
    }

    @Override
    public PhoneResponseDTO updated(Long id, PhoneRequestDTO updatedPhone) {
        return null;
    }

    @Override
    public Boolean deletePhone(Long id, String number) {
        return null;
    }

    private PhoneResponseDTO getPhoneToCreate(Long id) {
        try {
            return getPhone(id);
        } catch (Exception e) {
            return null;
        }
    }






}


