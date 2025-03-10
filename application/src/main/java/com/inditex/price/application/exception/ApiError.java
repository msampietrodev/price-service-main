package com.inditex.price.application.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor 
public class ApiError {
    private LocalDateTime timestamp;
    private String message;
    private List<String> errors;

    public ApiError(String message, List<String> errors) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.errors = errors;
    }

    public ApiError(String message, String error) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.errors = List.of(error);
    }
}
