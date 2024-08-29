package org.example.agronomyexpert.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.agronomyexpert.enums.CartStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "carrinho")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

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
    private Employee employeeFk;

    @Column(name = "valor_total_do_carrinho", nullable = false)
    private BigDecimal totalValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 10)
    private CartStatusEnum status;
}
