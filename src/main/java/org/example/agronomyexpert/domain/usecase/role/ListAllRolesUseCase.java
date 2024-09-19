package org.example.agronomyexpert.domain.usecase.role;

import org.example.agronomyexpert.domain.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListAllRolesUseCase {
    Page<Role> listAll(Pageable pageable);
}
