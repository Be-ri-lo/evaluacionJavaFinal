package com.nisum.evaluacionJava.controllers;

import com.nisum.evaluacionJava.dto.request.PhoneRequestDTO;
import com.nisum.evaluacionJava.entities.Phone;
import com.nisum.evaluacionJava.repositories.PhoneRepository;
import com.nisum.evaluacionJava.services.PhoneServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class PhoneController {

    PhoneServiceImpl phoneService;

    public PhoneController(PhoneServiceImpl phoneService) {
        this.phoneService = phoneService;
    }

    @PostMapping
    public ResponseEntity<Phone> saveUser(@RequestBody PhoneRequestDTO phoneRequestDTO) {
        return new ResponseEntity(phoneService.savePhone(phoneRequestDTO), HttpStatus.CREATED);
    }
}
