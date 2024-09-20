package org.example.agronomyexpert.domain.usecase.employee.impl;

import org.example.agronomyexpert.domain.exception.RoleNotFoundException;
import org.example.agronomyexpert.domain.model.Employee;
import org.example.agronomyexpert.domain.model.Role;
import org.example.agronomyexpert.domain.usecase.employee.CreateEmployeeUseCase;
import org.example.agronomyexpert.infrastructure.adapter.security.WebSecurityConfig;
import org.example.agronomyexpert.infrastructure.persistence.EmployeeRepository;
import org.example.agronomyexpert.infrastructure.persistence.RoleRepository;
import org.example.agronomyexpert.presentation.dto.request.CreateEmployeeDto;
import org.springframework.stereotype.Service;

@Service
public class CreateEmployeeUseCaseImpl implements CreateEmployeeUseCase {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final WebSecurityConfig securityConfiguration;

    public CreateEmployeeUseCaseImpl(EmployeeRepository employeeRepository,
                                     RoleRepository roleRepository,
                                     WebSecurityConfig securityConfiguration) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.securityConfiguration = securityConfiguration;
    }

    @Override
    public Employee createEmployee(CreateEmployeeDto createEmployeeDto) {
        Role role = roleRepository.findById(createEmployeeDto.roleId())
                .orElseThrow(() -> new RoleNotFoundException("Não foi encontrado nenhum cargo com o id informado"));

        String hashedPassword = encryptEmployeePassword(createEmployeeDto.password());

        Employee employee = Employee.create(createEmployeeDto, role, hashedPassword);
        return employeeRepository.save(employee);
    }

    private String encryptEmployeePassword(String password) {
        return securityConfiguration.passwordEncoder().encode(password);
    }
}
