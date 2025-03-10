package com.inditex.price.infrastructure.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.inditex.price.application.exception.ApiError;
import com.inditex.price.domain.repository.PriceRepository;

import reactor.core.publisher.Mono;

@SpringBootTest
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PriceControllerFallbackIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private PriceRepository priceRepository;
    
    @Test
    void testCircuitBreakerFallback() {
    	when(priceRepository.findSelectPrice(any(), any(), any()))
        .thenReturn(Mono.error(new RuntimeException("Error simulado")));

        IntStream.range(0, 5).forEach(i -> {
            webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path("/price/select")
                    .queryParam("productId", 35455)
                    .queryParam("brandId", 1)
                    .queryParam("applicationDate", "2020-06-14T10:00:00")
                    .build())
                .exchange()
                .expectStatus().is5xxServerError();
        });

        webTestClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/price/select")
            .queryParam("productId", 35455)
            .queryParam("brandId", 1)
            .queryParam("applicationDate", "2020-06-14T10:00:00")
            .build())
        .exchange()
        .expectStatus().isEqualTo(HttpStatus.SERVICE_UNAVAILABLE)
        .expectBody(ApiError.class)
        .consumeWith(response -> {
            ApiError apiError = response.getResponseBody();
            assert apiError != null;
            assertEquals("Price service is currently unavailable", apiError.getErrors().getFirst());
        });
    }
    
}
