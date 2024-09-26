package org.example.agronomyexpert.domain.usecase.employee;

import org.example.agronomyexpert.domain.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListAllEmployeesUseCase {
    Page<Employee> listAll(Pageable pageable);
}
