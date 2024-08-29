package org.example.agronomyexpert.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "pagamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_de_criacao", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "tipo", nullable = false, unique = true, length = 30)
    private String type;
}
