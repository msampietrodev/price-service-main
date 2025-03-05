package com.inditex.price.application.exception;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
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
