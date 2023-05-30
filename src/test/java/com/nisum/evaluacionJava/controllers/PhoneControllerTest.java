/*package com.nisum.evaluacionJava.controllers;

import com.nisum.evaluacionJava.dto.request.PhoneRequestDTO;
import com.nisum.evaluacionJava.dto.response.PhoneResponseDTO;
import com.nisum.evaluacionJava.services.PhoneServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
class PhoneControllerTest {

    @InjectMocks
    private PhoneController phoneController;

    @Mock
    PhoneServiceImpl mockedPhoneServiceImpl;

   /* @Test
    @DisplayName("Test saveUser in a success scenario")
    public void savePhone() {
       // PhoneRequestDTO phoneRequestDTO = new PhoneRequestDTO(1L,"12", "1", "12", null);
        PhoneResponseDTO phoneResponseDto = PhoneResponseDTO.builder().id(1L).phoneNumber("123").countryCode("12").cityCode("1").build();
        Mockito.when(mockedPhoneServiceImpl.savePhone(phoneRequestDTO)).thenReturn(phoneResponseDto);
        ResponseEntity<PhoneResponseDTO> response = phoneController.savePhone(phoneRequestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}*/
