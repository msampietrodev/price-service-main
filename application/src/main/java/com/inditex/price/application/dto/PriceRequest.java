package com.inditex.price.application.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public record PriceRequest(
    @NotNull(message = "Product ID cannot be null") Long productId,
    @NotNull(message = "Brand ID cannot be null") Long brandId,
    @NotNull(message = "Application date cannot be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate
) {}
