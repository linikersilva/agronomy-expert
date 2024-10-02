package org.example.agronomyexpert.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum StockOperationTypeEnum {
    ENTRADA,
    SAIDA;

    @JsonCreator
    public static StockOperationTypeEnum forValue(String value) {
        return Stream.of(StockOperationTypeEnum.values())
                .filter(enumValue -> enumValue.name().equals(value.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo de operação de estoque inválido"));
    }
}
