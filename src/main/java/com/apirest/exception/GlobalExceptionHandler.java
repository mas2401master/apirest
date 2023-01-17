package com.apirest.exception;

import com.apirest.dto.ErrorDetail;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorDetail> handleApiException(ApiException exception, WebRequest webRequest){
        ErrorDetail newError = new ErrorDetail(new Date(),exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<ErrorDetail>(newError,exception.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleGlobalsException(Exception exception,WebRequest webRequest){
        ErrorDetail newError = new ErrorDetail(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(newError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String namefield = ((FieldError)error).getField();
            String message = error.getDefaultMessage();

            errors.put(namefield, message);
        });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }


}
