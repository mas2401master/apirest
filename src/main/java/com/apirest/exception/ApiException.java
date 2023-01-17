package com.apirest.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    @Setter @Getter
    private HttpStatus status;
    @Setter @Getter
    private String message;

    public ApiException(HttpStatus httpStatus, String men){
        super();
        this.status = httpStatus;
        this.message = men;
    }
}
