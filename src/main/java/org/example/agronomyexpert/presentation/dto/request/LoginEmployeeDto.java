package org.example.agronomyexpert.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginEmployeeDto(@NotBlank(message = "O atributo username não pode ser nulo ou vazio")
                               String username,
                               @NotBlank(message = "O atributo password não pode ser nulo ou vazio")
                               String password) {}
