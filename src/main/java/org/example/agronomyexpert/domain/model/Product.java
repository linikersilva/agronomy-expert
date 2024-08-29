package org.example.agronomyexpert.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_de_criacao", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "nome", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "preco", nullable = false)
    private BigDecimal price;

    @Column(name = "quantidade_em_estoque_atual", nullable = false)
    private Integer stockQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_fk", nullable = false)
    private Category categoryFk;

    @Column(name = "urlFoto", nullable = false, length = 100)
    private String photoUrl;
}
