package org.example.agronomyexpert.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginEmployeeDto(@NotBlank(message = "O atributo username não pode ser nulo ou vazio")
                               @Size(max = 10, message = "O tamanho máximo do atributo username é 10 caracteres")
                               String username,
                               @NotBlank(message = "O atributo password não pode ser nulo ou vazio")
                               String password) {}
