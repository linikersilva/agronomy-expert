package org.example.agronomyexpert.presentation.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UpdateProductDto(@Size(max = 50, message = "O tamanho máximo do atributo name é 50 caracteres")
                               String name,
                               @DecimalMin(value = "1.00", message = "O atributo price não pode ser zero ou negativo")
                               @DecimalMax(value = "100000.00", message = "O atributo price deve ser de no máximo 10000.00 (dez mil)")
                               BigDecimal price,
                               Integer stockQuantity,
                               Integer categoryId,
                               @Size(max = 100, message = "O tamanho máximo do atributo photoUrl é 100 caracteres")
                               String photoUrl) {}
