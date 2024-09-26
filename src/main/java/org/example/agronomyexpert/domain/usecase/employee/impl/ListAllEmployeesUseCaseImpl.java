package org.example.agronomyexpert.domain.usecase.employee.impl;

import org.example.agronomyexpert.domain.model.Employee;
import org.example.agronomyexpert.domain.usecase.employee.ListAllEmployeesUseCase;
import org.example.agronomyexpert.infrastructure.persistence.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListAllEmployeesUseCaseImpl implements ListAllEmployeesUseCase {

    private final EmployeeRepository employeeRepository;

    public ListAllEmployeesUseCaseImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Page<Employee> listAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }
}
