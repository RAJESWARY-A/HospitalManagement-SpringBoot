   package com.ty.hospitalapp.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
   public class GlobalExceptionHandler {

       @ExceptionHandler(MethodArgumentNotValidException.class)
       @ResponseStatus(HttpStatus.BAD_REQUEST)
       public Map<String, Object> handleValidation(MethodArgumentNotValidException ex) {
           Map<String, String> errors = new LinkedHashMap<>();
           for (FieldError error : ex.getBindingResult().getFieldErrors()) {
               errors.put(error.getField(), error.getDefaultMessage());
           }
           Map<String, Object> body = new LinkedHashMap<>();
           body.put("status", 400);
           body.put("message", "Validation failed");
           body.put("errors", errors);
           return body;
       }

       @ExceptionHandler(ResponseStatusException.class)
       public ResponseEntity<Map<String, Object>> handleStatus(ResponseStatusException ex) {
           Map<String, Object> body = new LinkedHashMap<>();
           body.put("status", ex.getStatusCode().value());
           body.put("message", ex.getReason());
           return ResponseEntity.status(ex.getStatusCode()).body(body);
       }
   }
