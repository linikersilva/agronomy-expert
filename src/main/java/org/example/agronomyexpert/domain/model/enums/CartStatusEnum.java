package org.example.agronomyexpert.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum CartStatusEnum {
    ATIVO,
    FINALIZADO,
    CANCELADO;

    @JsonCreator
    public static CartStatusEnum forValue(String value) {
        return Stream.of(CartStatusEnum.values())
                .filter(enumValue -> enumValue.name().equals(value.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Status de carrinho inválido"));
    }
}
