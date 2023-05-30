package com.nisum.evaluacionJava.dto.request;

import com.nisum.evaluacionJava.entities.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateRequestDTO {
    private String name;
    private String email;
    private String password;
    private Boolean isActive;
    private List<Phone> phones;
}
