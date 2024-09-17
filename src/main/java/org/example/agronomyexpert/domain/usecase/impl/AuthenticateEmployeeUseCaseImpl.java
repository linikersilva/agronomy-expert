package org.example.agronomyexpert.domain.usecase.impl;

import org.example.agronomyexpert.domain.usecase.AuthenticateEmployeeUseCase;
import org.example.agronomyexpert.infrastructure.adapter.security.JwtTokenService;
import org.example.agronomyexpert.infrastructure.adapter.security.UserDetailsImpl;
import org.example.agronomyexpert.presentation.dto.request.LoginEmployeeDto;
import org.example.agronomyexpert.presentation.dto.response.RecoveryJwtTokenDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateEmployeeUseCaseImpl implements AuthenticateEmployeeUseCase {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public AuthenticateEmployeeUseCaseImpl(AuthenticationManager authenticationManager,
                                           JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public RecoveryJwtTokenDto authenticateEmployee(LoginEmployeeDto loginEmployeeDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginEmployeeDto.username(), loginEmployeeDto.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String token = jwtTokenService.generateToken(userDetails);
        return jwtTokenService.buildResponseDto(token);
    }
}
