package org.example.agronomyexpert.domain.usecase.role;

import org.example.agronomyexpert.domain.model.Role;
import org.example.agronomyexpert.presentation.dto.request.CreateRoleDto;

public interface CreateRoleUseCase {
    Role createRole(CreateRoleDto createRoleDto);
}
