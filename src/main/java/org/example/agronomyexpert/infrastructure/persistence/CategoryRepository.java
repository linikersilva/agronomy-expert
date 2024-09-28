package org.example.agronomyexpert.infrastructure.persistence;

import org.example.agronomyexpert.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
