package com.nisum.evaluacionJava.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneResponseDTO {
    private Long id;
    private String phoneNumber;
    private String cityCode;
    private String countryCode;
    private UserIdResponseDTO userId;

   /*public static PhoneResponseDTO fromRepository (Phone phone) {
       UserResponseDTO parsedUser = UserResponseDTO.builder()
               .id(phone.getId())
               .build();
       return PhoneResponseDTO.builder()
               .id(phone.getId())
               .phoneNumber(phone.getPhoneNumber())
               .cityCode(phone.getCityCode())
               .countryCode(phone.getCountryCode())
               .build();
   }*/
}
