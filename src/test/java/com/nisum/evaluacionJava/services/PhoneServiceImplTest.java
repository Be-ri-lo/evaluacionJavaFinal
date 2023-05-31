package com.nisum.evaluacionJava.services;

import com.nisum.evaluacionJava.dto.request.PhoneRequestDTO;
import com.nisum.evaluacionJava.dto.response.PhoneResponseDTO;
import com.nisum.evaluacionJava.entities.Phone;
import com.nisum.evaluacionJava.exceptions.CustomEx;
import com.nisum.evaluacionJava.repositories.PhoneRepository;
import com.nisum.evaluacionJava.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit tests of PhoneServiceImpl class")
public class PhoneServiceImplTest {

    @Mock
    private PhoneRepository phoneRepository;
    private ModelMapper modelMapper;
    private UserRepository userRepository;
    private Phone phoneTest;
    private PhoneRequestDTO phoneRequestDTOTest;
    private PhoneServiceImpl phoneServiceImpl;
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    public void beforeEachTest() {
        MockitoAnnotations.openMocks(this);

        modelMapper = new ModelMapper();
        phoneTest = Phone.builder().id(1L).phoneNumber("223455").cityCode("12").countryCode("12").build();
        phoneRequestDTOTest = PhoneRequestDTO.builder().phoneNumber("223455").cityCode("12").countryCode("12").build();
    }

    @Test
    @DisplayName("Save a phone")
    void savePhone() {
        when(phoneRepository.save(any())).thenReturn(Phone.builder().id(1L).build());
        PhoneServiceImpl phoneServiceImpl = new PhoneServiceImpl(phoneRepository, userRepository, modelMapper);
        PhoneResponseDTO phoneResponseDTO = phoneServiceImpl.savePhone(phoneRequestDTOTest);

        assertEquals(1L, phoneResponseDTO.getId());
    }

    @Test
    @DisplayName("Error al guardar el teléfono")
    public void testSavePhone_Error() {

        PhoneRequestDTO phoneRequestDTO = new PhoneRequestDTO();
        phoneRequestDTO.setPhoneNumber("123456789");
        phoneRequestDTO.setCityCode("123");
        phoneRequestDTO.setCountryCode("456");

        when(phoneRepository.save(any(Phone.class))).thenThrow(new CustomEx("Error: Teléfono no se ha podido guardar", HttpStatus.INTERNAL_SERVER_ERROR));
        PhoneServiceImpl phoneServiceImpl = new PhoneServiceImpl(phoneRepository, userRepository,modelMapper);

        assertThrows(CustomEx.class, () -> phoneServiceImpl.savePhone(phoneRequestDTO));
    }

    @Test
    @DisplayName("get Phone by id")
    public void getPhoneById_validId() {
        when(phoneRepository.findById(any())).thenReturn(Optional.of(phoneTest));

        PhoneServiceImpl phoneServiceImpl = new PhoneServiceImpl(phoneRepository, null, modelMapper);
        PhoneResponseDTO result = phoneServiceImpl.getPhone(1L);

        assertNotNull(result);
        verify(phoneRepository).findById(1L);

    }

    @Test
    @DisplayName("Obtener un id invalido")
    public void testGetPhone_InvalidId() {
        Long invalidId = 999L;

        when(phoneRepository.findById(invalidId)).thenReturn(Optional.empty());

        PhoneServiceImpl phoneServiceImpl = new PhoneServiceImpl(phoneRepository, null, modelMapper);
        PhoneResponseDTO result = phoneServiceImpl.getPhone(invalidId);

        assertNull(result);

        verify(phoneRepository).findById(invalidId);

    }
}