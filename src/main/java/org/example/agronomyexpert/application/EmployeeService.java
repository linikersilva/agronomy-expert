package org.example.agronomyexpert.application;

import org.example.agronomyexpert.domain.model.Employee;
import org.example.agronomyexpert.domain.usecase.authentication.AuthenticateEmployeeUseCase;
import org.example.agronomyexpert.domain.usecase.employee.CreateEmployeeUseCase;
import org.example.agronomyexpert.domain.usecase.employee.ListAllEmployeesUseCase;
import org.example.agronomyexpert.domain.usecase.employee.UpdateEmployeeUseCase;
import org.example.agronomyexpert.presentation.dto.request.CreateEmployeeDto;
import org.example.agronomyexpert.presentation.dto.request.EmployeeResponseDto;
import org.example.agronomyexpert.presentation.dto.request.LoginEmployeeDto;
import org.example.agronomyexpert.presentation.dto.request.UpdateEmployeeDto;
import org.example.agronomyexpert.presentation.dto.response.RecoveryJwtTokenDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

@Service
public class EmployeeService {

    private final AuthenticateEmployeeUseCase authenticateEmployeeUseCase;
    private final CreateEmployeeUseCase createEmployeeUseCase;
    private final UpdateEmployeeUseCase updateEmployeeUseCase;
    private final ListAllEmployeesUseCase listAllEmployeesUseCase;

    public EmployeeService(AuthenticateEmployeeUseCase authenticateEmployeeUseCase,
                           CreateEmployeeUseCase createEmployeeUseCase,
                           UpdateEmployeeUseCase updateEmployeeUseCase,
                           ListAllEmployeesUseCase listAllEmployeesUseCase) {
        this.authenticateEmployeeUseCase = authenticateEmployeeUseCase;
        this.createEmployeeUseCase = createEmployeeUseCase;
        this.updateEmployeeUseCase = updateEmployeeUseCase;
        this.listAllEmployeesUseCase = listAllEmployeesUseCase;
    }

    public RecoveryJwtTokenDto authenticateEmployee(LoginEmployeeDto loginEmployeeDto) {
        return authenticateEmployeeUseCase.authenticateEmployee(loginEmployeeDto);
    }

    @Transactional(readOnly = true)
    public Page<EmployeeResponseDto> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);

        return listAllEmployeesUseCase.listAll(pageable)
                .map(this::buildEmployeeResponseDto);
    }

    @Transactional
    public EmployeeResponseDto createEmployee(CreateEmployeeDto createEmployeeDto) {
        Employee savedEmployee = createEmployeeUseCase.createEmployee(createEmployeeDto);

        return buildEmployeeResponseDto(savedEmployee);
    }

    @Transactional
    public EmployeeResponseDto updateEmployee(Integer id, UpdateEmployeeDto updateEmployeeDto) {
        Employee updateEmployee = updateEmployeeUseCase.updateEmployee(id, updateEmployeeDto);

        return buildEmployeeResponseDto(updateEmployee);
    }

    private EmployeeResponseDto buildEmployeeResponseDto(Employee employee) {
        return new EmployeeResponseDto(employee.getId(),
                employee.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                employee.getName(),
                employee.getUsername(),
                "**********",
                employee.getCpf(),
                employee.getPhone(),
                employee.getDdd(),
                employee.getEmail(),
                employee.getStreet(),
                employee.getNeighborhood(),
                employee.getCity(),
                employee.getUf(),
                employee.getNumber(),
                employee.getCep(),
                employee.getGender(),
                employee.getBirthdate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                employee.getRoleFk().getId(),
                employee.getStatus());
    }
}
