package com.inditex.price.infrastructure.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.BiFunction;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;

import com.inditex.price.domain.model.Price;
import com.inditex.price.domain.repository.PriceRepository;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class PriceRepositoryImpl implements PriceRepository {

    private final DatabaseClient databaseClient;

    private static final BiFunction<Row, RowMetadata, Price> MAPPING_FUNCTION = (row, rowMetadata) -> new Price(
            row.get("id", Long.class),
            row.get("brand_id", Long.class),
            row.get("product_id", Long.class),
            row.get("price_list", Integer.class),
            row.get("start_date", LocalDateTime.class),
            row.get("end_date", LocalDateTime.class),
            row.get("price", BigDecimal.class),
            row.get("currency", String.class),
            row.get("priority", Integer.class)
    );

    private static final String SELECT_PRICE_QUERY = 
        "SELECT * FROM prices WHERE product_id = :productId AND brand_id = :brandId " +
        "AND start_date <= :applicationDate AND end_date >= :applicationDate ORDER BY priority DESC, price ASC LIMIT 1";

    @Override
    public Mono<Price> findSelectPrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        return databaseClient.sql(SELECT_PRICE_QUERY)
                .bind("productId", productId)
                .bind("brandId", brandId)
                .bind("applicationDate", applicationDate)
                .map(MAPPING_FUNCTION)
                .one();
    }
}
