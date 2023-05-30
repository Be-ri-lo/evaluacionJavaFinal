package com.nisum.evaluacionJava.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneRequestDTO {
    private String phoneNumber;
    private String cityCode;
    private String countryCode;

}
