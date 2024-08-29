package org.example.agronomyexpert.domain.model.embedded;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.example.agronomyexpert.domain.model.Cart;
import org.example.agronomyexpert.domain.model.Product;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartProductPk {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrinho_fk", nullable = false)
    private Cart cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_fk", nullable = false)
    private Product productId;
}
