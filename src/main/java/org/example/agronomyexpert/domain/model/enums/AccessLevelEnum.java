package org.example.agronomyexpert.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum AccessLevelEnum {
    BASICO,
    ADMINISTRATIVO;

    @JsonCreator
    public static AccessLevelEnum forValue(String value) {
        return Stream.of(AccessLevelEnum.values())
                .filter(enumValue -> enumValue.name().equals(value.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("O atributo accessLevel informado não corresponde a um nível de acesso válido"));
    }
}
