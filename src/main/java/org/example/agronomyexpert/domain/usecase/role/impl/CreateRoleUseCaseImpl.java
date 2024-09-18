package org.example.agronomyexpert.domain.usecase.role.impl;

import org.example.agronomyexpert.domain.model.Role;
import org.example.agronomyexpert.domain.usecase.role.CreateRoleUseCase;
import org.example.agronomyexpert.infrastructure.persistence.RoleRepository;
import org.example.agronomyexpert.presentation.dto.request.CreateRoleDto;
import org.springframework.stereotype.Service;

@Service
public class CreateRoleUseCaseImpl implements CreateRoleUseCase {

    private final RoleRepository roleRepository;

    public CreateRoleUseCaseImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(CreateRoleDto createRoleDto) {
        Role role = Role.create(createRoleDto.name(), createRoleDto.salary(), createRoleDto.accessLevel());
        return roleRepository.save(role);
    }
}
