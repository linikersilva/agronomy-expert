package org.example.agronomyexpert.presentation.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateProductDto(@NotBlank(message = "O atributo name não pode ser nulo ou vazio")
                               @Size(max = 50, message = "O tamanho máximo do atributo name é 50 caracteres")
                               String name,
                               @NotNull(message = "O atributo price não pode ser nulo")
                               @DecimalMin(value = "1.00", message = "O atributo price não pode ser zero ou negativo")
                               @DecimalMax(value = "100000.00", message = "O atributo price deve ser de no máximo 10000.00 (dez mil)")
                               BigDecimal price,
                               @NotNull(message = "O atributo stockQuantity não pode ser nulo")
                               Integer stockQuantity,
                               @NotNull(message = "O atributo categoryId não pode ser nulo")
                               Integer categoryId,
                               @NotBlank(message = "O atributo photoUrl não pode ser nulo ou vazio")
                               @Size(max = 100, message = "O tamanho máximo do atributo photoUrl é 100 caracteres")
                               String photoUrl) {}
