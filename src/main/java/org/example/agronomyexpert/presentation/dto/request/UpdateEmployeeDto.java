package org.example.agronomyexpert.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.example.agronomyexpert.domain.model.enums.EmployeeStatusEnum;
import org.example.agronomyexpert.domain.model.enums.GenderEnum;

import java.time.LocalDate;

public record UpdateEmployeeDto(@Size(max = 50, message = "O tamanho máximo do atributo name é 50 caracteres")
                                String name,
                                @Size(min = 10, max = 10, message = "O tamanho do atributo username deve ser 10 caracteres")
                                String username,
                                @Size(min = 8, max = 16, message = "O tamanho do atributo password deve ser entre 8 a 16 caracteres")
                                String password,
                                @Size(min = 11, max = 11, message = "O tamanho do atributo cpf deve ser 11 caracteres")
                                String cpf,
                                @Size(min = 9, max = 9, message = "O tamanho do atributo phone deve ser 9 caracteres")
                                String phone,
                                @Size(min = 2, max = 2, message = "O tamanho do atributo ddd deve ser 2 caracteres")
                                String ddd,
                                @Size(max = 50, message = "O tamanho máximo do atributo email é 50 caracteres")
                                @Email(message = "Email inválido")
                                String email,
                                @Size(max = 30, message = "O tamanho máximo do atributo street é 30 caracteres")
                                String street,
                                @Size(max = 30, message = "O tamanho máximo do atributo neighborhood é 30 caracteres")
                                String neighborhood,
                                @Size(max = 30, message = "O tamanho máximo do atributo city é 30 caracteres")
                                String city,
                                @Size(min = 2, max = 2, message = "O tamanho do atributo uf deve ser 2 caracteres")
                                String uf,
                                @Size(max = 4, message = "O tamanho máximo do atributo number é 4 caracteres")
                                String number,
                                @Size(min = 8, max = 8, message = "O tamanho do atributo cep deve ser 8 caracteres")
                                String cep,
                                GenderEnum gender,
                                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
                                LocalDate birthdate,
                                Integer roleId,
                                EmployeeStatusEnum status) {}
