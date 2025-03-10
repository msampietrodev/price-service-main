package com.inditex.price.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("prices")
public record Price(
    @Id Long id,
    Long brandId,
    Long productId,
    Integer priceList,
    LocalDateTime startDate,
    LocalDateTime endDate,
    BigDecimal price,
    String currency,
    Integer priority
) {}
