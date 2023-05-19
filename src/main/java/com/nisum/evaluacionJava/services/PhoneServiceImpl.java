package com.nisum.evaluacionJava.services;

import com.nisum.evaluacionJava.entities.Phone;
import com.nisum.evaluacionJava.repositories.PhoneRepository;
import org.springframework.stereotype.Service;

@Service
public class PhoneServiceImpl implements PhoneService{

    private PhoneRepository phoneRepository;

    public PhoneServiceImpl(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    public Phone savePhone(Phone phone) {
        return phoneRepository.save(phone);
    }

   /* @Override
    public Phone getPhone(String number) {
        return null;
    }

    @Override
    public Phone updated(Long id, Phone updatedPhone) {
        return null;
    }

    @Override
    public Boolean deletePhone(Long id, String number) {
        return null;
    }*/
}
