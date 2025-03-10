package com.inditex.price.application.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.naming.ServiceUnavailableException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Mono<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex) {
		log.error("Validation error occurred: {}", ex.getMessage(), ex);
		List<String> errors = ex.getBindingResult().getAllErrors().stream().map(error -> {
			String fieldName = ((FieldError) error).getField();
			return String.format("%s: %s", fieldName, error.getDefaultMessage());
		}).collect(Collectors.toList());
		ApiError apiError = new ApiError("Validation error", errors);
		return Mono.just(apiError);
	}

	@ExceptionHandler(PriceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Mono<ApiError> handlePriceNotFoundException(PriceNotFoundException ex) {
		log.error("Price not found: {}", ex.getMessage(), ex);
		
		ApiError apiError = new ApiError("Price Not Found", ex.getMessage());
		return Mono.just(apiError);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Mono<ApiError> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
		log.error("Bad request: {}", ex.getMessage(), ex);
		ApiError apiError = new ApiError("Bad Request", "Request body must not be null or malformed");
		return Mono.just(apiError);
	}

    @ExceptionHandler(ServiceUnavailableException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Mono<ApiError> handleServiceUnavailableException(ServiceUnavailableException ex) {
    	log.error("Service unavailable: {}", ex.getMessage(), ex);
        ApiError apiError = new ApiError("Service Unavailable", ex.getMessage());
        return Mono.just(apiError);
    }
}
