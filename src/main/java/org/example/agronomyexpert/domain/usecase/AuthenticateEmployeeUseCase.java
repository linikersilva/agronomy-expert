package org.example.agronomyexpert.domain.usecase;

import org.example.agronomyexpert.presentation.dto.request.LoginEmployeeDto;
import org.example.agronomyexpert.presentation.dto.response.RecoveryJwtTokenDto;

public interface AuthenticateEmployeeUseCase {
    RecoveryJwtTokenDto authenticateEmployee(LoginEmployeeDto loginEmployeeDto);
}
