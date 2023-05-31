package com.nisum.evaluacionJava.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomEx extends RuntimeException{

    private HttpStatus code;

    public CustomEx (String message , HttpStatus code){
        super(message);
        this.code = code;
    }


}