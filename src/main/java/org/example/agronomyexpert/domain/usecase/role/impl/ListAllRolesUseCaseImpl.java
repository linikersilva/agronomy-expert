package org.example.agronomyexpert.domain.usecase.role.impl;

import org.example.agronomyexpert.domain.model.Role;
import org.example.agronomyexpert.domain.usecase.role.ListAllRolesUseCase;
import org.example.agronomyexpert.infrastructure.persistence.RoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListAllRolesUseCaseImpl implements ListAllRolesUseCase {

    private final RoleRepository roleRepository;

    public ListAllRolesUseCaseImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Page<Role> listAll(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }
}
