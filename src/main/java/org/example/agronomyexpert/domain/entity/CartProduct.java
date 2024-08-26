package org.example.agronomyexpert.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;
import org.example.agronomyexpert.domain.entity.embedded.CartProductPk;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "carrinho_produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartProduct {

    @EmbeddedId
    private CartProductPk id;

    @Column(name = "quantidade", nullable = false)
    private Integer quantity;

    @Column(name = "preco", nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(name = "data", nullable = false)
    private LocalDateTime createdAt;
}
