package org.example.agronomyexpert.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.agronomyexpert.domain.model.enums.StockOperationTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "historico_estoque")
@Getter
@Setter
public class Stock {

    public Stock() {
    }

    public static Stock create(final Product product, final Integer quantity,
                               final StockOperationTypeEnum operationType) {
        return new Stock(product, quantity, operationType);
    }

    public Stock(final Product product, final Integer quantity, final StockOperationTypeEnum operationType) {
        if (product == null) {
            throw new IllegalArgumentException("O produto que está sendo registrado no histórico de estoque não pode ser nulo");
        }
        this.productFk = product;

        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("A quantidade do produto que está sendo registrado no histórico de estoque não pode ser nula nem zero ou negativa");
        }
        this.quantity = quantity;

        if (operationType == null) {
            throw new IllegalArgumentException("O tipo de operação do registro no histórico de estoque não pode ser nulo");
        }
        this.operationType = operationType;

        this.createdAt = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private Product productFk;

    @Column(name = "quantidade", nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_operacao", nullable = false, length = 7)
    private StockOperationTypeEnum operationType;

    @Column(name = "data_de_lancamento", nullable = false)
    private LocalDateTime createdAt;
}
