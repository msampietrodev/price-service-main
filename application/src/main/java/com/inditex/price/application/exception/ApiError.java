package com.inditex.price.application.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ApiError(
    LocalDateTime timestamp,
    String message,
    List<String> errors
) {
    public ApiError(String message, List<String> errors) {
        this(LocalDateTime.now(), message, errors);
    }

    public ApiError(String message, String error) {
        this(LocalDateTime.now(), message, List.of(error));
    }
}
