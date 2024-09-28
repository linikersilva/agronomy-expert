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
import org.example.agronomyexpert.domain.model.enums.GenderEnum;
import org.example.agronomyexpert.presentation.dto.request.CreateClientDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "cliente")
@Getter
@Setter
public class Client {

    public Client() {
    }

    public static Client create(final CreateClientDto createClientDto) {
        return new Client(createClientDto);
    }

    public Client(final CreateClientDto createClientDto) {
        if (createClientDto.name() == null || createClientDto.name().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente não pode ser nulo ou vazio");
        }
        this.name = createClientDto.name();

        if (createClientDto.cpfCnpj() == null || createClientDto.cpfCnpj().trim().isEmpty() || createClientDto.cpfCnpj().length() != 11 && createClientDto.cpfCnpj().length() != 14) {
            throw new IllegalArgumentException("O cpfCnpj do cliente não pode ser nulo ou vazio e deve ter ou 11 ou 14 caracteres");
        }
        this.cpfCnpj = createClientDto.cpfCnpj();

        if (createClientDto.phone() == null || createClientDto.phone().trim().isEmpty()) {
            throw new IllegalArgumentException("O telefone do cliente não pode ser nulo ou vazio");
        }
        this.phone = createClientDto.phone();

        if (createClientDto.ddd() == null || createClientDto.ddd().trim().isEmpty()) {
            throw new IllegalArgumentException("O ddd do cliente não pode ser nulo ou vazio");
        }
        this.ddd = createClientDto.ddd();

        if (createClientDto.email() == null || createClientDto.email().trim().isEmpty()) {
            throw new IllegalArgumentException("O email do cliente não pode ser nulo ou vazio");
        }
        this.email = createClientDto.email();

        if (createClientDto.street() == null || createClientDto.street().trim().isEmpty()) {
            throw new IllegalArgumentException("A rua do cliente não pode ser nula ou vazia");
        }
        this.street = createClientDto.street();

        if (createClientDto.neighborhood() == null || createClientDto.neighborhood().trim().isEmpty()) {
            throw new IllegalArgumentException("O bairro do cliente não pode ser nulo ou vazio");
        }
        this.neighborhood = createClientDto.neighborhood();

        if (createClientDto.city() == null || createClientDto.city().trim().isEmpty()) {
            throw new IllegalArgumentException("A cidade do cliente não pode ser nula ou vazia");
        }
        this.city = createClientDto.city();

        if (createClientDto.uf() == null || createClientDto.uf().trim().isEmpty()) {
            throw new IllegalArgumentException("O estado do cliente não pode ser nulo ou vazio");
        }
        this.uf = createClientDto.uf().toUpperCase();

        if (createClientDto.number() == null || createClientDto.number().trim().isEmpty()) {
            throw new IllegalArgumentException("O número do cliente não pode ser nulo ou vazio");
        }
        this.number = createClientDto.number();

        if (createClientDto.cep() == null || createClientDto.cep().trim().isEmpty()) {
            throw new IllegalArgumentException("O cep do cliente não pode ser nulo ou vazio");
        }
        this.cep = createClientDto.cep();

        if (createClientDto.gender() == null) {
            throw new IllegalArgumentException("O gênero do cliente não pode ser nulo");
        }
        this.gender = createClientDto.gender();

        if (createClientDto.birthdate() == null || createClientDto.birthdate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de nascimento do cliente não pode ser nula nem igual ou posterior a hoje");
        }
        this.birthdate = createClientDto.birthdate();

        this.createdAt = LocalDateTime.now();
    }

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
