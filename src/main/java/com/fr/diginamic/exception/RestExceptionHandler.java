package com.fr.diginamic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler (value = {InvalidResourceException.class})
    public ResponseEntity<String> handlenvalidReoussourceException(InvalidResourceException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format(e.getMessage()));
    }

    @ExceptionHandler (value = {ResourceNotFound.class})
    public ResponseEntity<String> handleRessourceNotFound(ResourceNotFound e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format(e.getMessage()));
    }

}
