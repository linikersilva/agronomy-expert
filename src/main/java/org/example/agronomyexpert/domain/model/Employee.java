package org.example.agronomyexpert.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.example.agronomyexpert.domain.model.enums.EmployeeStatusEnum;
import org.example.agronomyexpert.domain.model.enums.GenderEnum;
import org.example.agronomyexpert.presentation.dto.request.CreateEmployeeDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "funcionario")
@Getter
@Setter
public class Employee {

    public Employee() {
    }

    public static Employee create(final CreateEmployeeDto createEmployeeDto, final Role role, String hashedPassword) {
        return new Employee(createEmployeeDto, role, hashedPassword);
    }

    public Employee(final CreateEmployeeDto createEmployeeDto, final Role role, String hashedPassword) {
        if (createEmployeeDto.name() == null || createEmployeeDto.name().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do funcionário não pode ser nulo ou vazio");
        }
        this.name = createEmployeeDto.name();

        if (createEmployeeDto.username() == null || createEmployeeDto.username().trim().isEmpty() || createEmployeeDto.username().length() != 10) {
            throw new IllegalArgumentException("O usuário do funcionário não pode ser nulo ou vazio e deve ter 10 caracteres");
        }
        this.username = createEmployeeDto.username().toUpperCase();

        if (hashedPassword == null || hashedPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("A senha do funcionário não pode ser nula ou vazia");
        }
        this.hashedPassword = hashedPassword;

        if (createEmployeeDto.cpf() == null || createEmployeeDto.cpf().trim().isEmpty() || createEmployeeDto.cpf().length() != 11) {
            throw new IllegalArgumentException("O cpf do funcionário não pode ser nulo ou vazio e deve ter 11 caracteres");
        }
        this.cpf = createEmployeeDto.cpf();

        if (createEmployeeDto.phone() == null || createEmployeeDto.phone().trim().isEmpty()) {
            throw new IllegalArgumentException("O telefone do funcionário não pode ser nulo ou vazio");
        }
        this.phone = createEmployeeDto.phone();

        if (createEmployeeDto.ddd() == null || createEmployeeDto.ddd().trim().isEmpty()) {
            throw new IllegalArgumentException("O ddd do funcionário não pode ser nulo ou vazio");
        }
        this.ddd = createEmployeeDto.ddd();

        if (createEmployeeDto.email() == null || createEmployeeDto.email().trim().isEmpty()) {
            throw new IllegalArgumentException("O email do funcionário não pode ser nulo ou vazio");
        }
        this.email = createEmployeeDto.email();

        if (createEmployeeDto.street() == null || createEmployeeDto.street().trim().isEmpty()) {
            throw new IllegalArgumentException("A rua do funcionário não pode ser nula ou vazia");
        }
        this.street = createEmployeeDto.street();

        if (createEmployeeDto.neighborhood() == null || createEmployeeDto.neighborhood().trim().isEmpty()) {
            throw new IllegalArgumentException("O bairro do funcionário não pode ser nulo ou vazio");
        }
        this.neighborhood = createEmployeeDto.neighborhood();

        if (createEmployeeDto.city() == null || createEmployeeDto.city().trim().isEmpty()) {
            throw new IllegalArgumentException("A cidade do funcionário não pode ser nula ou vazia");
        }
        this.city = createEmployeeDto.city();

        if (createEmployeeDto.uf() == null || createEmployeeDto.uf().trim().isEmpty()) {
            throw new IllegalArgumentException("O estado do funcionário não pode ser nulo ou vazio");
        }
        this.uf = createEmployeeDto.uf().toUpperCase();

        if (createEmployeeDto.number() == null || createEmployeeDto.number().trim().isEmpty()) {
            throw new IllegalArgumentException("O número do funcionário não pode ser nulo ou vazio");
        }
        this.number = createEmployeeDto.number();

        if (createEmployeeDto.cep() == null || createEmployeeDto.cep().trim().isEmpty()) {
            throw new IllegalArgumentException("O cep do funcionário não pode ser nulo ou vazio");
        }
        this.cep = createEmployeeDto.cep();

        if (createEmployeeDto.gender() == null) {
            throw new IllegalArgumentException("O gênero do funcionário não pode ser nulo");
        }
        this.gender = createEmployeeDto.gender();

        if (createEmployeeDto.birthdate() == null || createEmployeeDto.birthdate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de nascimento do funcionário não pode ser nula nem igual ou posterior a hoje");
        }
        this.birthdate = createEmployeeDto.birthdate();

        if (role == null) {
            throw new IllegalArgumentException("O cargo do funcionário não pode ser nulo");
        }
        this.roleFk = role;

        this.createdAt = LocalDateTime.now();
        this.status = EmployeeStatusEnum.ATIVO;
    }

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
