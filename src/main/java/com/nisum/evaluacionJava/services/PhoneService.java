package com.nisum.evaluacionJava.services;

import com.nisum.evaluacionJava.dto.request.PhoneRequestDTO;
import com.nisum.evaluacionJava.dto.response.PhoneResponseDTO;

public interface PhoneService {

    PhoneResponseDTO savePhone(PhoneRequestDTO phoneRequestDTO);

    PhoneResponseDTO getPhone(Long id);

}
