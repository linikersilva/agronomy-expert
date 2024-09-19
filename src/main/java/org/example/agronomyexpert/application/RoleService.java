package org.example.agronomyexpert.application;

import org.example.agronomyexpert.domain.model.Role;
import org.example.agronomyexpert.domain.usecase.role.CreateRoleUseCase;
import org.example.agronomyexpert.domain.usecase.role.UpdateRoleUseCase;
import org.example.agronomyexpert.presentation.dto.request.CreateRoleDto;
import org.example.agronomyexpert.presentation.dto.request.RoleResponseDto;
import org.example.agronomyexpert.presentation.dto.request.UpdateRoleDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

    private final CreateRoleUseCase createRoleUseCase;
    private final UpdateRoleUseCase updateRoleUseCase;

    public RoleService(CreateRoleUseCase createRoleUseCase,
                       UpdateRoleUseCase updateRoleUseCase) {
        this.createRoleUseCase = createRoleUseCase;
        this.updateRoleUseCase = updateRoleUseCase;
    }

    @Transactional
    public RoleResponseDto createRole(CreateRoleDto createRoleDto) {
        Role savedRole = createRoleUseCase.createRole(createRoleDto);

        return new RoleResponseDto(savedRole.getId(),
                                   savedRole.getCreatedAt(),
                                   savedRole.getName(),
                                   savedRole.getSalary(),
                                   savedRole.getAccessLevel());
    }

    @Transactional
    public RoleResponseDto updateRole(Integer id, UpdateRoleDto updateRoleDto) {
        Role updatedRole = updateRoleUseCase.updateRole(id, updateRoleDto);

        return new RoleResponseDto(updatedRole.getId(),
                                   updatedRole.getCreatedAt(),
                                   updatedRole.getName(),
                                   updatedRole.getSalary(),
                                   updatedRole.getAccessLevel());
    }
}
