package org.example.agronomyexpert.application;

import org.example.agronomyexpert.domain.model.Role;
import org.example.agronomyexpert.domain.usecase.role.CreateRoleUseCase;
import org.example.agronomyexpert.presentation.dto.request.CreateRoleDto;
import org.example.agronomyexpert.presentation.dto.request.RoleResponseDto;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final CreateRoleUseCase createRoleUseCase;

    public RoleService(CreateRoleUseCase createRoleUseCase) {
        this.createRoleUseCase = createRoleUseCase;
    }

    public RoleResponseDto createRole(CreateRoleDto createRoleDto) {
        Role savedRole = createRoleUseCase.createRole(createRoleDto);

        return new RoleResponseDto(savedRole.getId(),
                                   savedRole.getCreatedAt(),
                                   savedRole.getName(),
                                   savedRole.getSalary(),
                                   savedRole.getAccessLevel());
    }
}
