package org.example.agronomyexpert.application;

import org.example.agronomyexpert.infrastructure.adapter.security.JwtTokenService;
import org.example.agronomyexpert.infrastructure.adapter.security.UserDetailsImpl;
import org.example.agronomyexpert.infrastructure.adapter.security.WebSecurityConfig;
import org.example.agronomyexpert.presentation.dto.request.LoginEmployeeDto;
import org.example.agronomyexpert.presentation.dto.response.RecoveryJwtTokenDto;
import org.example.agronomyexpert.infrastructure.persistence.EmployeeRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final WebSecurityConfig securityConfiguration;
    private final RoleService roleService;
    private final EmployeeRepository employeeRepository;

    public EmployeeService(AuthenticationManager authenticationManager,
                           JwtTokenService jwtTokenService,
                           EmployeeRepository employeeRepository,
                           WebSecurityConfig securityConfiguration,
                           RoleService roleService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.employeeRepository = employeeRepository;
        this.securityConfiguration = securityConfiguration;
        this.roleService = roleService;
    }

    public RecoveryJwtTokenDto authenticateEmployee(LoginEmployeeDto loginEmployeeDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginEmployeeDto.username(), loginEmployeeDto.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String token = jwtTokenService.generateToken(userDetails);
        return new RecoveryJwtTokenDto(token,
                jwtTokenService.getExpiresAtFromToken(token));
    }

}
