package org.example.agronomyexpert.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.example.agronomyexpert.presentation.dto.request.CreateProductDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "produto")
@Getter
@Setter
public class Product {

    public Product() {
    }

    public static Product create(final CreateProductDto createProductDto, final Category category) {
        return new Product(createProductDto, category);
    }

    public Product(final CreateProductDto createProductDto, final Category category) {
        if (createProductDto.name() == null || createProductDto.name().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser nulo ou vazio");
        }
        this.name = createProductDto.name();

        if (createProductDto.price() == null || createProductDto.price().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O preço do produto não pode ser nulo ou negativo");
        }
        this.price = createProductDto.price();

        if (createProductDto.stockQuantity() == null) {
            throw new IllegalArgumentException("A quantidade em estoque do produto não pode ser nula");
        }
        this.stockQuantity = createProductDto.stockQuantity();

        if (category == null) {
            throw new IllegalArgumentException("A categoria do produto não pode ser nula");
        }
        this.categoryFk = category;

        if (createProductDto.photoUrl() == null || createProductDto.photoUrl().trim().isEmpty()) {
            throw new IllegalArgumentException("O url da foto do produto não pode ser nula ou vazia");
        }
        this.photoUrl = createProductDto.photoUrl();

        this.createdAt = LocalDateTime.now();
    }

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
