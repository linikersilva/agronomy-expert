package org.example.agronomyexpert.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.agronomyexpert.enums.AccesLevelEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "cargo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

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
    private AccesLevelEnum accessLevel;
}
