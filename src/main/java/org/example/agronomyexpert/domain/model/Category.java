package org.example.agronomyexpert.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.example.agronomyexpert.presentation.dto.request.CreateCategoryDto;

import java.time.LocalDateTime;

@Entity(name = "categoria")
@Getter
@Setter
public class Category {

    public Category() {
    }

    public static Category create(final CreateCategoryDto createCategoryDto) {
        return new Category(createCategoryDto);
    }

    public Category(final CreateCategoryDto createCategoryDto) {
        if (createCategoryDto.name() == null || createCategoryDto.name().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da categoria não pode ser nulo ou vazio");
        }
        this.name = createCategoryDto.name();

        this.createdAt = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_de_criacao", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "nome", nullable = false, unique = true, length = 30)
    private String name;
}
