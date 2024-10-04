package org.example.agronomyexpert.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;
import org.example.agronomyexpert.domain.model.embedded.CartProductPk;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "carrinho_produto")
@Getter
@Setter
public class CartProduct {

    public CartProduct() {
    }

    public static CartProduct create(final Cart cart, final Product product,
                                     final Integer quantity, final BigDecimal price) {
        return new CartProduct(cart, product, quantity, price);
    }

    public CartProduct(final Cart cart, final Product product,
                       final Integer quantity, final BigDecimal price) {
        if (cart == null) {
            throw new IllegalArgumentException("O carrinho do registro de relação entre carrinho-produto não pode ser nulo");
        }
        CartProductPk.CartProductPkBuilder cartProductPkBuilder = CartProductPk.builder().cartId(cart);

        if (product == null) {
            throw new IllegalArgumentException("O produto do registro de relação entre carrinho-produto não pode ser nulo");
        }
        cartProductPkBuilder.productId(product);
        this.id = cartProductPkBuilder.build();

        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("A quantidade do produto no registro de relação entre carrinho-produto não pode ser nula nem menor ou igual a zero");
        }
        if (quantity > product.getStockQuantity()) {
            throw new IllegalArgumentException("Estoque do produto é insuficiente");
        }
        this.quantity = quantity;

        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço do produto no registro de relação entre carrinho-produto não pode ser nulo nem menor ou igual a zero");
        }
        this.price = price;

        this.total = price.multiply(BigDecimal.valueOf(quantity));

        this.createdAt = LocalDateTime.now();
    }

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
