package org.example.agronomyexpert.domain.usecase.employee;

import org.example.agronomyexpert.domain.model.Employee;
import org.example.agronomyexpert.presentation.dto.request.UpdateEmployeeDto;

public interface UpdateEmployeeUseCase {

    Employee updateEmployee(Integer id, UpdateEmployeeDto updateEmployeeDto);
}
