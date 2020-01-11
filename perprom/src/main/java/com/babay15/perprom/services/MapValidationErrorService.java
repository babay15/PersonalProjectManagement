package com.babay15.perprom.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class MapValidationErrorService {

    public ResponseEntity<?> MapValidationService(BindingResult result) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("hasError", true);
        responseBody.put("status", HttpStatus.BAD_REQUEST.value());
        responseBody.put("message", "Invalid Object");

        Map<String, Object> detailedError = null;
        for (FieldError error : result.getFieldErrors()) {
            if (detailedError == null) {
                detailedError = new HashMap<>();
            }

            detailedError.put(error.getField(), error.getDefaultMessage());
        }

        if (detailedError != null) {
            responseBody.put("detailedError", detailedError);
        }

        return new ResponseEntity<Map<String, Object>>(responseBody, HttpStatus.BAD_REQUEST);
    }
}
