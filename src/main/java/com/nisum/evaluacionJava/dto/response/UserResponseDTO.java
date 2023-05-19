package com.nisum.evaluacionJava.dto.response;

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
public class UserResponseDTO implements Serializable {
    private Long id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private LocalDateTime lastLogin;
    private Boolean isActive;
    private String tokenId;
}
