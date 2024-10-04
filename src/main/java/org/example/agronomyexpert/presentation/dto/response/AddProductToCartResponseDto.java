package org.example.agronomyexpert.presentation.dto.response;

import java.math.BigDecimal;

public record AddProductToCartResponseDto(Integer cartId,
                                          Integer productId,
                                          Integer quantity,
                                          BigDecimal price,
                                          BigDecimal total,
                                          String createdAt) {}
