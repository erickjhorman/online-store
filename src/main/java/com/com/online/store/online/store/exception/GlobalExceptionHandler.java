package com.com.online.store.online.store.exception;


import com.com.online.store.online.store.config.CustomAccessDeniedHandler;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@RestControllerAdvice
@NoArgsConstructor
@Slf4j
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorResponse = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDetails> validateFields(ResponseStatusException ex, WebRequest request) {
        ErrorDetails errorResponse = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<ErrorDetails> resourceBadRequestException(ResourceBadRequestException ex, WebRequest request) {
        ErrorDetails errorResponse = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceMailException.class)
    public ResponseEntity<ErrorDetails> resourceMailException(ResourceMailException ex, WebRequest request) {
        ErrorDetails errorResponse = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> globalExceptionHandler (Exception ex, WebRequest request) {
        ErrorDetails errorResponse = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetails> accessDeniedException(AccessDeniedException ex, WebRequest request){
        ErrorDetails errorResponse = new ErrorDetails(new Date(), "Please ask for permission", request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

}
