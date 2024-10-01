package org.example.agronomyexpert.presentation.dto.response;

import org.example.agronomyexpert.domain.model.enums.CartStatusEnum;

import java.math.BigDecimal;

public record CartResponseDto(Integer id,
                              String createdAt,
                              Integer clientId,
                              Integer sellerId,
                              BigDecimal totalValue,
                              CartStatusEnum status) {
}
