package com.inditex.price.domain.repository;

import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import com.inditex.price.domain.model.Price;

public interface PriceRepository  {
    
    Mono<Price> findSelectPrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
