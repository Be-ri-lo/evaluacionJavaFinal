package com.nisum.evaluacionJava.services;

import com.nisum.evaluacionJava.dto.request.PhoneRequestDTO;
import com.nisum.evaluacionJava.dto.response.PhoneResponseDTO;
import com.nisum.evaluacionJava.entities.Phone;

import java.util.Optional;

public interface PhoneService {

    PhoneResponseDTO savePhone(PhoneRequestDTO phoneRequestDTO);

    PhoneResponseDTO getPhone(Long id);

}
