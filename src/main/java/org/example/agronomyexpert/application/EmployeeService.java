package org.example.agronomyexpert.application;

import org.example.agronomyexpert.domain.model.Employee;
import org.example.agronomyexpert.domain.usecase.authentication.AuthenticateEmployeeUseCase;
import org.example.agronomyexpert.domain.usecase.employee.CreateEmployeeUseCase;
import org.example.agronomyexpert.presentation.dto.request.CreateEmployeeDto;
import org.example.agronomyexpert.presentation.dto.request.EmployeeResponseDto;
import org.example.agronomyexpert.presentation.dto.request.LoginEmployeeDto;
import org.example.agronomyexpert.presentation.dto.response.RecoveryJwtTokenDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

@Service
public class EmployeeService {

    private final AuthenticateEmployeeUseCase authenticateEmployeeUseCase;
    private final CreateEmployeeUseCase createEmployeeUseCase;

    public EmployeeService(AuthenticateEmployeeUseCase authenticateEmployeeUseCase,
                           CreateEmployeeUseCase createEmployeeUseCase) {
        this.authenticateEmployeeUseCase = authenticateEmployeeUseCase;
        this.createEmployeeUseCase = createEmployeeUseCase;
    }

    public RecoveryJwtTokenDto authenticateEmployee(LoginEmployeeDto loginEmployeeDto) {
        return authenticateEmployeeUseCase.authenticateEmployee(loginEmployeeDto);
    }

    @Transactional
    public EmployeeResponseDto createEmployee(CreateEmployeeDto createEmployeeDto) {
        Employee savedEmployee = createEmployeeUseCase.createEmployee(createEmployeeDto);

        return new EmployeeResponseDto(savedEmployee.getId(),
                                       savedEmployee.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                                       savedEmployee.getName(),
                                       savedEmployee.getUsername(),
                                       "**********",
                                       savedEmployee.getCpf(),
                                       savedEmployee.getPhone(),
                                       savedEmployee.getDdd(),
                                       savedEmployee.getEmail(),
                                       savedEmployee.getStreet(),
                                       savedEmployee.getNeighborhood(),
                                       savedEmployee.getCity(),
                                       savedEmployee.getUf(),
                                       savedEmployee.getNumber(),
                                       savedEmployee.getCep(),
                                       savedEmployee.getGender(),
                                       savedEmployee.getBirthdate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                       savedEmployee.getRoleFk().getId(),
                                       savedEmployee.getStatus());
    }

}
