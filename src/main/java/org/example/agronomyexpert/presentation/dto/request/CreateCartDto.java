package org.example.agronomyexpert.presentation.dto.request;

import jakarta.validation.constraints.NotNull;

public record CreateCartDto(@NotNull(message = "O atributo clientId não pode ser nulo")
                            Integer clientId,
                            Integer sellerId) {}
