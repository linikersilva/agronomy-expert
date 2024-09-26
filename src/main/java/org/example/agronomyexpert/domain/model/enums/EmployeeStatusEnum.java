package org.example.agronomyexpert.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum EmployeeStatusEnum {
    ATIVO,
    INATIVO;

    @JsonCreator
    public static EmployeeStatusEnum forValue(String value) {
        return Stream.of(EmployeeStatusEnum.values())
                .filter(enumValue -> enumValue.name().equals(value.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("O atributo status informado não corresponde a um status de funcionário válido"));
    }
}
