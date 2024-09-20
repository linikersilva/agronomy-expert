package org.example.agronomyexpert.presentation.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.example.agronomyexpert.domain.model.enums.AccessLevelEnum;

import java.math.BigDecimal;

public record UpdateRoleDto(@Size(max = 30, message = "O tamanho máximo do atributo name é 30 caracteres")
                            String name,
                            @Min(value = 1, message = "O atributo salary não pode ser zero ou negativo")
                            BigDecimal salary,
                            AccessLevelEnum accessLevel) {}
