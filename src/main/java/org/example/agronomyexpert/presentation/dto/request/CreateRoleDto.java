package org.example.agronomyexpert.presentation.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.agronomyexpert.domain.model.enums.AccessLevelEnum;

import java.math.BigDecimal;

public record CreateRoleDto(@NotBlank(message = "O atributo name não pode ser nulo ou vazio")
                            String name,
                            @NotNull(message = "O atributo salary não pode ser nulo")
                            @Min(value = 1, message = "O atributo salary não pode ser zero")
                            BigDecimal salary,
                            @NotNull(message = "O atributo accessLevel não pode ser nulo")
                            AccessLevelEnum accessLevel) {}
