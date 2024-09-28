package org.example.agronomyexpert.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCategoryDto(@NotBlank(message = "O atributo name não pode ser nulo ou vazio")
                                @Size(max = 30, message = "O tamanho máximo do atributo name é 30 caracteres")
                                String name) {
}
