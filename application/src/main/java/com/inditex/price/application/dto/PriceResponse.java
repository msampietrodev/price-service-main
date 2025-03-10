package com.inditex.price.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record PriceResponse(
    Long brandId,
    Long productId,
    Integer priceList,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime startDate,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime endDate,
    BigDecimal price,
    String currency
) {}
