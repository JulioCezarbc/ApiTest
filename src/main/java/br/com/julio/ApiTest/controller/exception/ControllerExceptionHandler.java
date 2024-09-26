package br.com.julio.ApiTest.controller.exception;

import br.com.julio.ApiTest.service.exception.DataIntegrityViolationException;
import br.com.julio.ApiTest.service.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
        StandardError error = new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request){
        StandardError error = new StandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}

