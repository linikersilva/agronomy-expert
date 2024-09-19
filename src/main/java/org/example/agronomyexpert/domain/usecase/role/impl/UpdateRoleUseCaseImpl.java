package org.example.agronomyexpert.domain.usecase.role.impl;

import org.example.agronomyexpert.domain.exception.RoleNotFoundException;
import org.example.agronomyexpert.domain.model.Role;
import org.example.agronomyexpert.domain.usecase.role.UpdateRoleUseCase;
import org.example.agronomyexpert.infrastructure.persistence.RoleRepository;
import org.example.agronomyexpert.presentation.dto.request.UpdateRoleDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateRoleUseCaseImpl implements UpdateRoleUseCase {

    private final RoleRepository roleRepository;

    public UpdateRoleUseCaseImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role updateRole(Integer id, UpdateRoleDto updateRoleDto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Não foi encontrado nenhum cargo com o id informado"));

        Optional.ofNullable(updateRoleDto.name()).ifPresent(role::setName);
        Optional.ofNullable(updateRoleDto.salary()).ifPresent(role::setSalary);
        Optional.ofNullable(updateRoleDto.accessLevel()).ifPresent(role::setAccessLevel);

        return roleRepository.save(role);
    }
}
