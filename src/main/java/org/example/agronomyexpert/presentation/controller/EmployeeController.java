package org.example.agronomyexpert.presentation.controller;

import jakarta.validation.Valid;
import org.example.agronomyexpert.presentation.dto.request.CreateEmployeeDto;
import org.example.agronomyexpert.presentation.dto.request.EmployeeResponseDto;
import org.example.agronomyexpert.presentation.dto.request.LoginEmployeeDto;
import org.example.agronomyexpert.presentation.dto.response.RecoveryJwtTokenDto;
import org.example.agronomyexpert.application.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateEmployee(@RequestBody @Valid LoginEmployeeDto loginEmployeeDto) {
        RecoveryJwtTokenDto token = employeeService.authenticateEmployee(loginEmployeeDto);
        return ResponseEntity.ok().body(token);
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody @Valid CreateEmployeeDto createEmployeeDto) {
        EmployeeResponseDto employee = employeeService.createEmployee(createEmployeeDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(employee.id()).toUri();
        return ResponseEntity.created(uri).body(employee);
    }

}
