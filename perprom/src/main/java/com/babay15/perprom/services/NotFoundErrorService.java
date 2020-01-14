package com.babay15.perprom.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotFoundErrorService {

    public ResponseEntity<?> NotFoundErrorService(String projectIdentifier){
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("hasError", true);
        responseBody.put("status", HttpStatus.NOT_FOUND.value());
        responseBody.put("message", "Invalid Object");
        Map<String, Object> detailedError = new HashMap<>();
        detailedError.put("projectIdentifier", "Project ID " + projectIdentifier + " is not exist");
        responseBody.put("data", detailedError);

        return new ResponseEntity<Map<String, Object>>(responseBody, HttpStatus.NOT_FOUND);
    }
}
