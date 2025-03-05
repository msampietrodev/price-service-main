package com.inditex.price.domain.repository;

import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import com.inditex.price.domain.model.Price;

/**
 * Repository interface for managing prices in the system.
 */
public interface PriceRepository  {
    
    /**
     * Finds the applicable price for a given product and brand based on the application date.
     *
     * @param productId      The ID of the product
     * @param brandId       The ID of the brand
     * @param applicationDate The date when the price should be applied
     * @return A Mono containing the applicable Price, or empty if not found
     */
    Mono<Price> findSelectPrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
