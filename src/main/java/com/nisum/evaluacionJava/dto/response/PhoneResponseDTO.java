package com.nisum.evaluacionJava.dto.response;


import com.nisum.evaluacionJava.entities.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneResponseDTO {
    private Long id;
    private String number;
    private String cityCode;
    private String countryCode;

    public static PhoneResponseDTO from (Phone phone) {
        return PhoneResponseDTO.builder()
                .id(phone.getId())
                .number(phone.getNumber())
                .cityCode(phone.getCityCode())
                .countryCode(phone.getCountryCode())
                .build();
    }
}
