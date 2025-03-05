package com.inditex.price.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.inditex.price.application.dto.PriceResponse;
import com.inditex.price.domain.model.Price;

@Mapper
public interface PriceMapper {
    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    PriceResponse toPriceResponse(Price price);
}

