package com.nisum.evaluacionJava.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreationObjectDTO implements Serializable {
    private Long id;
    private String uri;
}
