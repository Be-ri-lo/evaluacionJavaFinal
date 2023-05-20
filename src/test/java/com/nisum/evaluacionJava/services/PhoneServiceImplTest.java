package com.nisum.evaluacionJava.services;

import com.nisum.evaluacionJava.controllers.PhoneController;
import com.nisum.evaluacionJava.dto.request.PhoneRequestDTO;
import com.nisum.evaluacionJava.dto.response.PhoneResponseDTO;
import com.nisum.evaluacionJava.entities.Phone;
import com.nisum.evaluacionJava.exceptions.CustomEx;
import com.nisum.evaluacionJava.repositories.PhoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit tests of PhoneServiceImpl class")
class PhoneServiceImplTest {

    @Mock
    private PhoneRepository phoneRepository;
    private ModelMapper modelMapper;
    private Phone phoneTest;
    private PhoneRequestDTO phoneRequestDTOTest;

    @BeforeEach
    public void beforeEachTest() {
        phoneTest = Phone.builder().id(1L).phoneNumber("223455").cityCode("12").countryCode("12").build();
        phoneRequestDTOTest = PhoneRequestDTO.builder().id(1L).phoneNumber("223455").cityCode("12").countryCode("12").build();
    }

    @Test
    @DisplayName("Save a phone")
    void savePhone() {
        when(phoneRepository.save(any())).thenReturn(Phone.builder().id(1L).build());
        PhoneServiceImpl phoneServiceImpl = new PhoneServiceImpl(phoneRepository, modelMapper);
        PhoneResponseDTO phoneResponseDTO = phoneServiceImpl.savePhone(phoneRequestDTOTest);

        assertEquals(1L, phoneResponseDTO.getId());
    }

    @Test
    @DisplayName("get Phone by id")
    public void getPhone() {
        when(phoneRepository.findById(any())).thenReturn(Optional.of(phoneTest));

        PhoneServiceImpl phoneServiceImpl = new PhoneServiceImpl(phoneRepository, modelMapper);
        Optional<Phone> result = phoneServiceImpl.getPhone(1L);

        assertTrue(true, String.valueOf(phoneTest.getId() > 0));
        assertEquals("223455", phoneTest.getPhoneNumber());
        assertEquals("12", phoneTest.getCityCode());
    }

    @Test
    @DisplayName("get Phone to create")
    public void getPhoneToCreate() {
        when(phoneRepository.findById(any())).thenReturn(Optional.of(phoneTest));

        PhoneServiceImpl phoneServiceImpl = new PhoneServiceImpl(phoneRepository, modelMapper);
        Optional<Phone> result = phoneServiceImpl.getPhoneToCreate(1L);

        assertTrue(true, String.valueOf(phoneTest.getId() > 0));
        assertEquals("223455", phoneTest.getPhoneNumber());
        assertEquals("12", phoneTest.getCityCode());
    }

    @Test
    @DisplayName("Save a phone but exist an Custom Exception")
    public void savePhoneCustomException() {
        CustomEx customEx = new CustomEx("Error: Teléfono no se ha podido guardar", HttpStatus.INTERNAL_SERVER_ERROR);
        CustomEx exception = assertThrows(CustomEx.class, () -> {
            PhoneServiceImpl phoneService = Mockito.mock(PhoneServiceImpl.class);

            when(phoneService.savePhone(any())).thenThrow(customEx);
            PhoneController phoneController = new PhoneController(phoneService);

            phoneController.savePhone(any());
        });

        assertEquals(exception, customEx);
    }
}