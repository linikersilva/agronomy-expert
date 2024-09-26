package org.example.agronomyexpert.domain.usecase.employee.impl;

import org.example.agronomyexpert.domain.exception.EmployeeNotFoundException;
import org.example.agronomyexpert.domain.exception.RoleNotFoundException;
import org.example.agronomyexpert.domain.model.Employee;
import org.example.agronomyexpert.domain.model.Role;
import org.example.agronomyexpert.domain.usecase.employee.UpdateEmployeeUseCase;
import org.example.agronomyexpert.infrastructure.adapter.security.WebSecurityConfig;
import org.example.agronomyexpert.infrastructure.persistence.EmployeeRepository;
import org.example.agronomyexpert.infrastructure.persistence.RoleRepository;
import org.example.agronomyexpert.presentation.dto.request.UpdateEmployeeDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateEmployeeUseCaseImpl implements UpdateEmployeeUseCase {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final WebSecurityConfig securityConfiguration;

    public UpdateEmployeeUseCaseImpl(EmployeeRepository employeeRepository,
                                     RoleRepository roleRepository,
                                     WebSecurityConfig securityConfiguration) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.securityConfiguration = securityConfiguration;
    }

    @Override
    public Employee updateEmployee(Integer id, UpdateEmployeeDto updateEmployeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Não foi encontrado nenhum funcionário com o id informado"));

        Optional.ofNullable(updateEmployeeDto.name()).ifPresent(employee::setName);
        Optional.ofNullable(updateEmployeeDto.username()).ifPresent(newUsername -> employee.setUsername(newUsername.toUpperCase()));
        Optional.ofNullable(updateEmployeeDto.password()).ifPresent(newPassword -> employee.setHashedPassword(encryptEmployeePassword(newPassword)));
        Optional.ofNullable(updateEmployeeDto.cpf()).ifPresent(employee::setCpf);
        Optional.ofNullable(updateEmployeeDto.phone()).ifPresent(employee::setPhone);
        Optional.ofNullable(updateEmployeeDto.ddd()).ifPresent(employee::setDdd);
        Optional.ofNullable(updateEmployeeDto.email()).ifPresent(employee::setEmail);
        Optional.ofNullable(updateEmployeeDto.street()).ifPresent(employee::setStreet);
        Optional.ofNullable(updateEmployeeDto.neighborhood()).ifPresent(employee::setNeighborhood);
        Optional.ofNullable(updateEmployeeDto.city()).ifPresent(employee::setCity);
        Optional.ofNullable(updateEmployeeDto.uf()).ifPresent(newUf -> employee.setUf(newUf.toUpperCase()));
        Optional.ofNullable(updateEmployeeDto.number()).ifPresent(employee::setNumber);
        Optional.ofNullable(updateEmployeeDto.cep()).ifPresent(employee::setCep);
        Optional.ofNullable(updateEmployeeDto.gender()).ifPresent(employee::setGender);
        Optional.ofNullable(updateEmployeeDto.birthdate()).ifPresent(employee::setBirthdate);
        Optional.ofNullable(updateEmployeeDto.roleId()).ifPresent(newRoleId -> {
            Role role = roleRepository.findById(newRoleId)
                    .orElseThrow(() -> new RoleNotFoundException("Não foi encontrado nenhum cargo com o id informado"));
            employee.setRoleFk(role);
        });
        Optional.ofNullable(updateEmployeeDto.status()).ifPresent(employee::setStatus);

        employeeRepository.save(employee);
        return employee;
    }

    private String encryptEmployeePassword(String password) {
        return securityConfiguration.passwordEncoder().encode(password);
    }
}
