package org.example.agronomyexpert.domain.usecase.role;

import org.example.agronomyexpert.domain.model.Role;
import org.example.agronomyexpert.presentation.dto.request.UpdateRoleDto;

public interface UpdateRoleUseCase {
    Role updateRole(Integer id, UpdateRoleDto updateRoleDto);
}
