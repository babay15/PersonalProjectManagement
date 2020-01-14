package com.babay15.perprom.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectIdException(ProjectIdException ex, WebRequest request){
        ProjectIdExceptionResponse exceptionResponse = new ProjectIdExceptionResponse(ex.getMessage());
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("hasError", true);
        responseBody.put("status", HttpStatus.BAD_REQUEST.value());
        responseBody.put("message", "Invalid Object");
        responseBody.put("data", exceptionResponse);
        return new ResponseEntity(responseBody, HttpStatus.BAD_REQUEST);
    }
}
