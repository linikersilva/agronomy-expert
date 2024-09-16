package org.example.agronomyexpert.controller;

import jakarta.validation.Valid;
import org.example.agronomyexpert.controller.dto.LoginEmployeeDto;
import org.example.agronomyexpert.controller.dto.RecoveryJwtTokenDto;
import org.example.agronomyexpert.domain.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
