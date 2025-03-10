package com.inditex.price.infrastructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuitBreakerconfig {

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> slowCustomizer() {
        return factory -> factory.configure(builder ->
                        builder.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build()),
                "slow");
    }

}
