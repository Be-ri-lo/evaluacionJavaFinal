package com.nisum.evaluacionJava.dto.request;

import com.nisum.evaluacionJava.entities.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO implements Serializable {
    private String name;
    private String email;
    private String password;
    private Boolean active;
    private LocalDateTime dayOfLogin;
    private PhoneRequestDTO phoneId;

}
