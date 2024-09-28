package org.example.agronomyexpert.application;

import org.example.agronomyexpert.domain.model.Role;
import org.example.agronomyexpert.domain.usecase.role.CreateRoleUseCase;
import org.example.agronomyexpert.domain.usecase.role.ListAllRolesUseCase;
import org.example.agronomyexpert.domain.usecase.role.UpdateRoleUseCase;
import org.example.agronomyexpert.presentation.dto.request.CreateRoleDto;
import org.example.agronomyexpert.presentation.dto.response.RoleResponseDto;
import org.example.agronomyexpert.presentation.dto.request.UpdateRoleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

    private final CreateRoleUseCase createRoleUseCase;
    private final UpdateRoleUseCase updateRoleUseCase;
    private final ListAllRolesUseCase listAllRolesUseCase;

    public RoleService(CreateRoleUseCase createRoleUseCase,
                       UpdateRoleUseCase updateRoleUseCase,
                       ListAllRolesUseCase listAllRolesUseCase) {
        this.createRoleUseCase = createRoleUseCase;
        this.updateRoleUseCase = updateRoleUseCase;
        this.listAllRolesUseCase = listAllRolesUseCase;
    }

    @Transactional(readOnly = true)
    public Page<RoleResponseDto> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);

        return listAllRolesUseCase.listAll(pageable)
                .map(role -> new RoleResponseDto(role.getId(),
                        role.getCreatedAt(),
                        role.getName(),
                        role.getSalary(),
                        role.getAccessLevel()
                ));
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
