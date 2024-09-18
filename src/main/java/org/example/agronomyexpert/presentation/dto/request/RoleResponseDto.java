package org.example.agronomyexpert.presentation.dto.request;

import org.example.agronomyexpert.domain.model.enums.AccessLevelEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RoleResponseDto(Integer id,
                              LocalDateTime createdAt,
                              String name,
                              BigDecimal salary,
                              AccessLevelEnum accessLevel) {}
