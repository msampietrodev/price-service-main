package com.inditex.price.application.service;

import org.springframework.stereotype.Service;

import com.inditex.price.application.dto.PriceRequest;
import com.inditex.price.application.dto.PriceResponse;
import com.inditex.price.application.mapper.PriceMapper;
import com.inditex.price.domain.repository.PriceRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PriceService {
    private final PriceRepository priceRepository;
    
    public Mono<PriceResponse> selectPrice(PriceRequest priceRequest) {
        
        return priceRepository.findSelectPrice(priceRequest.getProductId(), priceRequest.getBrandId(), priceRequest.getApplicationDate())
        		.map(price -> PriceMapper.INSTANCE.toPriceResponse(price));
    }
}
