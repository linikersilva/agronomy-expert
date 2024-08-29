package org.example.agronomyexpert.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.agronomyexpert.shared.enums.GenderEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_de_criacao", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "nome", nullable = false, length = 50)
    private String name;

    @Column(name = "cpf_cnpj", nullable = false, unique = true, length = 14)
    private String cpfCnpj;

    @Column(name = "telefone", nullable = false, length = 9)
    private String phone;

    @Column(nullable = false, length = 2)
    private String ddd;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(name = "rua", nullable = false, length = 30)
    private String street;

    @Column(name = "bairro", nullable = false, length = 30)
    private String neighborhood;

    @Column(name = "cidade", nullable = false, length = 30)
    private String city;

    @Column(nullable = false, length = 2)
    private String uf;

    @Column(name = "numero", nullable = false, length = 4)
    private String number;

    @Column(nullable = false, length = 8)
    private String cep;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero", nullable = false, length = 1)
    private GenderEnum gender;

    @Column(name = "data_de_nascimento", nullable = false)
    private LocalDate birthdate;
}
