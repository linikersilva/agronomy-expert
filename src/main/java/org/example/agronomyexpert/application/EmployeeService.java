package org.example.agronomyexpert.application;

import org.example.agronomyexpert.domain.usecase.AuthenticateEmployeeUseCase;
import org.example.agronomyexpert.presentation.dto.request.LoginEmployeeDto;
import org.example.agronomyexpert.presentation.dto.response.RecoveryJwtTokenDto;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final AuthenticateEmployeeUseCase authenticateEmployeeUseCase;

    public EmployeeService(AuthenticateEmployeeUseCase authenticateEmployeeUseCase) {
        this.authenticateEmployeeUseCase = authenticateEmployeeUseCase;
    }

    public RecoveryJwtTokenDto authenticateEmployee(LoginEmployeeDto loginEmployeeDto) {
        return authenticateEmployeeUseCase.authenticateEmployee(loginEmployeeDto);
    }

}
