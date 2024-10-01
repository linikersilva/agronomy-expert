package org.example.agronomyexpert.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.example.agronomyexpert.domain.model.enums.CartStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "carrinho")
@Getter
@Setter
public class Cart {

    public Cart() {
    }

    public static Cart create(final Client client, final Employee seller) {
        return new Cart(client, seller);
    }

    public Cart(final Client client, final Employee seller) {
        if (client == null) {
            throw new IllegalArgumentException("O cliente do carrinho não pode ser nulo");
        }
        this.clientFk = client;

        if (seller == null) {
            throw new IllegalArgumentException("O vendedor do carrinho não pode ser nulo");
        }
        this.sellerFk = seller;

        this.createdAt = LocalDateTime.now();
        this.totalValue = BigDecimal.ZERO;
        this.status = CartStatusEnum.ATIVO;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_de_criacao", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_fk", nullable = false)
    private Client clientFk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_fk", nullable = false)
    private Employee sellerFk;

    @Column(name = "valor_total_do_carrinho", nullable = false)
    private BigDecimal totalValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 10)
    private CartStatusEnum status;
}
