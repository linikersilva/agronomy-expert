package org.example.agronomyexpert.presentation.dto.response;

import java.math.BigDecimal;

public record ProductResponseDto(Integer id,
                                 String createdAt,
                                 String name,
                                 BigDecimal price,
                                 Integer stockQuantity,
                                 Integer categoryId,
                                 String photoUrl) {
}
