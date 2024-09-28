package org.example.agronomyexpert.presentation.dto.request;

import jakarta.validation.constraints.Size;

public record UpdateCategoryDto(@Size(max = 30, message = "O tamanho máximo do atributo name é 30 caracteres")
                                String name) {
}
