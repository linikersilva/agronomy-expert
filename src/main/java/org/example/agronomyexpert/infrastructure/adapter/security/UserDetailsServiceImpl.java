package org.example.agronomyexpert.infrastructure.adapter.security;

import org.example.agronomyexpert.domain.model.Employee;
import org.example.agronomyexpert.infrastructure.persistence.EmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    public UserDetailsServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));
        return new UserDetailsImpl(employee);
    }

}
