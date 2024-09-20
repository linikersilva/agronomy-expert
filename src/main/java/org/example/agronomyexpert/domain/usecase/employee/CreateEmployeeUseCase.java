package org.example.agronomyexpert.domain.usecase.employee;

import org.example.agronomyexpert.domain.model.Employee;
import org.example.agronomyexpert.presentation.dto.request.CreateEmployeeDto;

public interface CreateEmployeeUseCase {

    Employee createEmployee(CreateEmployeeDto createEmployeeDto);
}
