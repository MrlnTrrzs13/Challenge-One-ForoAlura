package com.alura.challengeforoalura.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BugHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400(MethodArgumentNotValidException e) {
        var errors = e.getFieldErrors().stream().map(validationDataError::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    private record validationDataError(String campo, String error) {
        public validationDataError(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
