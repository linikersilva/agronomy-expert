package org.example.agronomyexpert.presentation.dto.request;

import jakarta.validation.constraints.NotNull;

public record CartProductDto(@NotNull(message = "O atributo cartId não pode ser nulo")
                             Integer cartId,
                             @NotNull(message = "O atributo productId não pode ser nulo")
                             Integer productId,
                             @NotNull(message = "O atributo quantity não pode ser nulo")
                             Integer quantity) {}
