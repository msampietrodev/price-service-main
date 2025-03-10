package com.inditex.price.infrastructure.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.inditex.price.application.dto.PriceResponse;
import com.inditex.price.application.exception.ApiError;
import com.inditex.price.domain.repository.PriceRepository;

import reactor.core.publisher.Mono;

@SpringBootTest
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PriceControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;
    
    @Test
    public void testSelectPriceAtTenAMOnJune14() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/price/select")
                        .queryParam("productId", 35455)
                        .queryParam("brandId", 1)
                        .queryParam("applicationDate", "2020-06-14T10:00:00")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(PriceResponse.class)
                .consumeWith(response -> {
                    PriceResponse body = response.getResponseBody();
                    assert body != null;
                    assertEquals(BigDecimal.valueOf(35.50).setScale(2, RoundingMode.HALF_UP), body.getPrice().setScale(2, RoundingMode.HALF_UP));
                });
    }

    @Test
    public void testSelectPriceAtFourPMOnJune14() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/price/select")
                        .queryParam("productId", 35455)
                        .queryParam("brandId", 1)
                        .queryParam("applicationDate", "2020-06-14T16:00:00")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(PriceResponse.class)
                .consumeWith(response -> {
                    PriceResponse body = response.getResponseBody();
                    assert body != null;
                    assertEquals(BigDecimal.valueOf(25.45).setScale(2, RoundingMode.HALF_UP), body.getPrice().setScale(2, RoundingMode.HALF_UP));
                });
    }

    @Test
    public void testSelectPriceAtNinePMOnJune14() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/price/select")
                        .queryParam("productId", 35455)
                        .queryParam("brandId", 1)
                        .queryParam("applicationDate", "2020-06-14T21:00:00")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(PriceResponse.class)
                .consumeWith(response -> {
                    PriceResponse body = response.getResponseBody();
                    assert body != null;
                    assertEquals(BigDecimal.valueOf(35.50).setScale(2, RoundingMode.HALF_UP), body.getPrice().setScale(2, RoundingMode.HALF_UP));
                });
    }

    @Test
    public void testSelectPriceAtTenAMOnJune15() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/price/select")
                        .queryParam("productId", 35455)
                        .queryParam("brandId", 1)
                        .queryParam("applicationDate", "2020-06-15T10:00:00")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(PriceResponse.class)
                .consumeWith(response -> {
                    PriceResponse body = response.getResponseBody();
                    assert body != null;
                    assertEquals(BigDecimal.valueOf(30.50).setScale(2, RoundingMode.HALF_UP), body.getPrice().setScale(2, RoundingMode.HALF_UP));
                });
    }

    @Test
    public void testSelectPriceAtNinePMOnJune16() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/price/select")
                        .queryParam("productId", 35455)
                        .queryParam("brandId", 1)
                        .queryParam("applicationDate", "2020-06-16T21:00:00")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(PriceResponse.class)
                .consumeWith(response -> {
                    PriceResponse body = response.getResponseBody();
                    assert body != null;
                    assertEquals(BigDecimal.valueOf(38.95).setScale(2, RoundingMode.HALF_UP), body.getPrice().setScale(2, RoundingMode.HALF_UP));
                });
    }
}
