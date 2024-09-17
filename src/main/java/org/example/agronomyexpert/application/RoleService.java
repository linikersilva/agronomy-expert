package org.example.agronomyexpert.application;

import org.example.agronomyexpert.infrastructure.persistence.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


}
