package com.nisum.evaluacionJava.dto.request;

import com.nisum.evaluacionJava.dto.response.UserIdResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneRequestDTO {
    private Long id;
    private String phoneNumber;
    private String cityCode;
    private String countryCode;
    //private UserIdResponseDTO userId;

    /*public static PhoneRequestDTO fromRepository (Phone phone) {
        return PhoneRequestDTO.builder()
                .id(phone.getId())
                .phoneNumber(phone.getPhoneNumber())
                .cityCode(phone.getCityCode())
                .countryCode(phone.getCountryCode())
                .build();
    }*/
}
