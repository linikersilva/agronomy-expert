package org.example.agronomyexpert.presentation.dto.request;

import jakarta.validation.constraints.Min;
import org.example.agronomyexpert.domain.model.enums.AccessLevelEnum;

import java.math.BigDecimal;

public record UpdateRoleDto(String name,
                            @Min(value = 1, message = "O atributo salary não pode ser zero")
                            BigDecimal salary,
                            AccessLevelEnum accessLevel) {}
