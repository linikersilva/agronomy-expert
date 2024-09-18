package org.example.agronomyexpert.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.example.agronomyexpert.domain.model.enums.AccessLevelEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "cargo")
@Getter
@Setter
public class Role {

    public Role() {
    }

    public static Role create(final String name, final BigDecimal salary, final AccessLevelEnum accessLevel) {
        return new Role(name, salary, accessLevel);
    }

    public Role(final String name, final BigDecimal salary, final AccessLevelEnum accessLevel) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cargo não pode ser nulo ou vazio");
        }
        this.name = name.toUpperCase();

        if (salary == null || salary.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O salário do cargo não pode ser nulo ou negativo");
        }
        this.salary = salary;

        if (accessLevel == null) {
            throw new IllegalArgumentException("O nível de acesso do cargo não pode ser nulo");
        }
        this.accessLevel = accessLevel;

        this.createdAt = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_de_criacao", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "nome", nullable = false, unique = true, length = 30)
    private String name;

    @Column(name = "salario", nullable = false)
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_de_acesso", nullable = false, length = 14)
    private AccessLevelEnum accessLevel;
}
