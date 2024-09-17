package org.example.agronomyexpert.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.agronomyexpert.domain.model.enums.StockOperationTypeEnum;

import java.time.LocalDateTime;

@Entity(name = "historico_estoque")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {

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
