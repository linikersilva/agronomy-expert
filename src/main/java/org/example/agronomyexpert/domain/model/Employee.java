package org.example.agronomyexpert.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.agronomyexpert.domain.model.enums.EmployeeStatusEnum;
import org.example.agronomyexpert.domain.model.enums.GenderEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "funcionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_de_criacao", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "nome", nullable = false, length = 50)
    private String name;

    @Column(name = "usuario", nullable = false, unique = true, length = 10)
    private String username;

    @Column(name = "senha_hash", nullable = false)
    private String hashedPassword;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cargo_fk", nullable = false)
    private Role roleFk;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 7)
    private EmployeeStatusEnum status;
}
