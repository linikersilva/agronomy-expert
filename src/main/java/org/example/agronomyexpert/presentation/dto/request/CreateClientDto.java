package org.example.agronomyexpert.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.agronomyexpert.domain.model.enums.GenderEnum;

import java.time.LocalDate;

public record CreateClientDto(@NotBlank(message = "O atributo name não pode ser nulo ou vazio")
                              @Size(max = 50, message = "O tamanho máximo do atributo name é 50 caracteres")
                              String name,
                              @NotBlank(message = "O atributo cpfCnpj não pode ser nulo ou vazio")
                              @Size(min = 11, max = 14, message = "O tamanho do atributo cpfCnpj deve ser ou 11 ou 14 caracteres")
                              String cpfCnpj,
                              @NotBlank(message = "O atributo phone não pode ser nulo ou vazio")
                              @Size(min = 9, max = 9, message = "O tamanho do atributo phone deve ser 9 caracteres")
                              String phone,
                              @NotBlank(message = "O atributo ddd não pode ser nulo ou vazio")
                              @Size(min = 2, max = 2, message = "O tamanho do atributo ddd deve ser 2 caracteres")
                              String ddd,
                              @NotBlank(message = "O atributo email não pode ser nulo ou vazio")
                              @Size(max = 50, message = "O tamanho máximo do atributo email é 50 caracteres")
                              @Email(message = "Email inválido")
                              String email,
                              @NotBlank(message = "O atributo street não pode ser nulo ou vazio")
                              @Size(max = 30, message = "O tamanho máximo do atributo street é 30 caracteres")
                              String street,
                              @NotBlank(message = "O atributo neighborhood não pode ser nulo ou vazio")
                              @Size(max = 30, message = "O tamanho máximo do atributo neighborhood é 30 caracteres")
                              String neighborhood,
                              @NotBlank(message = "O atributo city não pode ser nulo ou vazio")
                              @Size(max = 30, message = "O tamanho máximo do atributo city é 30 caracteres")
                              String city,
                              @NotBlank(message = "O atributo uf não pode ser nulo ou vazio")
                              @Size(min = 2, max = 2, message = "O tamanho do atributo uf deve ser 2 caracteres")
                              String uf,
                              @NotBlank(message = "O atributo number não pode ser nulo ou vazio")
                              @Size(max = 4, message = "O tamanho máximo do atributo number é 4 caracteres")
                              String number,
                              @NotBlank(message = "O atributo cep não pode ser nulo ou vazio")
                              @Size(min = 8, max = 8, message = "O tamanho do atributo cep deve ser 8 caracteres")
                              String cep,
                              @NotNull(message = "O atributo gender não pode ser nulo")
                              GenderEnum gender,
                              @NotNull(message = "O atributo birthdate não pode ser nulo")
                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
                              LocalDate birthdate) {}
