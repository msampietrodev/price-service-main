package com.inditex.price.infrastructure.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.price.application.dto.PriceRequest;
import com.inditex.price.application.dto.PriceResponse;
import com.inditex.price.application.exception.PriceNotFoundException;
import com.inditex.price.application.service.PriceService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/price") 
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    @GetMapping("/select")
    public Mono<PriceResponse> selectPrice(@Valid @ModelAttribute PriceRequest priceRequest) {
        return priceService.selectPrice(priceRequest)
                .switchIfEmpty(Mono.error(new PriceNotFoundException("Price not found for given parameters")));
    }
    

}
