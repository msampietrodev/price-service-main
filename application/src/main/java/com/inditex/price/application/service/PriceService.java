package com.inditex.price.application.service;

import javax.naming.ServiceUnavailableException;

import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
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
	private final ReactiveCircuitBreakerFactory<?, ?> reactiveCircuitBreakerFactory;

	public Mono<PriceResponse> selectPrice(PriceRequest priceRequest) {
		ReactiveCircuitBreaker circuitBreaker = reactiveCircuitBreakerFactory.create("priceService");

		return circuitBreaker.run(
				priceRepository.findSelectPrice(priceRequest.productId(), priceRequest.brandId(),
						priceRequest.applicationDate()).map(PriceMapper.INSTANCE::toPriceResponse),
				throwable -> getPriceFallback(priceRequest, throwable));
	}

	public Mono<PriceResponse> getPriceFallback(PriceRequest priceRequest, Throwable t) {
		return Mono.error(new ServiceUnavailableException("Price service is currently unavailable"));
	}
}
