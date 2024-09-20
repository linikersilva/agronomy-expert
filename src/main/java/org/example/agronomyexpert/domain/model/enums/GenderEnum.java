package org.example.agronomyexpert.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum GenderEnum {
    M,
    F;

    @JsonCreator
    public static GenderEnum forValue(String value) {
        return Stream.of(GenderEnum.values())
                .filter(enumValue -> enumValue.name().equals(value.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("O atributo gender informado não corresponde a um gênero válido"));
    }
}
