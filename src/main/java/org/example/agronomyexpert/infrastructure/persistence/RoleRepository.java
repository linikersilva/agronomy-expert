package org.example.agronomyexpert.infrastructure.persistence;

import org.example.agronomyexpert.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
