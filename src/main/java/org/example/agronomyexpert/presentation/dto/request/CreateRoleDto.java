package org.example.agronomyexpert.presentation.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.agronomyexpert.domain.model.enums.AccessLevelEnum;

import java.math.BigDecimal;

public record CreateRoleDto(@NotBlank(message = "O atributo name não pode ser nulo ou vazio")
                            @Size(max = 30, message = "O tamanho máximo do atributo name é 30 caracteres")
                            String name,
                            @NotNull(message = "O atributo salary não pode ser nulo")
                            @Min(value = 1, message = "O atributo salary não pode ser zero ou negativo")
                            BigDecimal salary,
                            @NotNull(message = "O atributo accessLevel não pode ser nulo")
                            AccessLevelEnum accessLevel) {}
