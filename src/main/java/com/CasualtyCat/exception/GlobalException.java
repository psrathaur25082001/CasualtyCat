package com.CasualtyCat.exception;

import com.CasualtyCat.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handelResourceNotFoundException(ResourceNotFoundException e){
        ApiResponse response=ApiResponse.builder().message(e.getMessage()).status(HttpStatus.NOT_FOUND).timeStamp(new Date()).success(true).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handelExceptions(Exception e){
        ApiResponse response=ApiResponse.builder().message(e.getMessage()).status(HttpStatus.NOT_FOUND).timeStamp(new Date()).success(true).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
