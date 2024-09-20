package org.example.agronomyexpert.presentation.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.agronomyexpert.domain.model.enums.AccessLevelEnum;

import java.math.BigDecimal;

public record CreateRoleDto(@NotBlank(message = "O atributo name não pode ser nulo ou vazio")
                            @Size(max = 20, message = "O tamanho máximo do atributo name é 20 caracteres")
                            String name,
                            @NotNull(message = "O atributo salary não pode ser nulo")
                            @DecimalMin(value = "1.00", message = "O atributo salary não pode ser zero ou negativo")
                            @DecimalMax(value = "100000.00", message = "O atributo salary deve ser de no máximo 100000.00 (cem mil)")
                            BigDecimal salary,
                            @NotNull(message = "O atributo accessLevel não pode ser nulo")
                            AccessLevelEnum accessLevel) {}
