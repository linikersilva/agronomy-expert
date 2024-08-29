package org.example.agronomyexpert.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;
import org.example.agronomyexpert.domain.model.embedded.CartPaymentPk;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "carrinho_pagamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartPayment {

    @EmbeddedId
    private CartPaymentPk id;

    @Column(name = "valor", nullable = false)
    private BigDecimal value;

    @Column(name = "data", nullable = false)
    private LocalDateTime createdAt;
}
