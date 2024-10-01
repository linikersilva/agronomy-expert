package org.example.agronomyexpert.infrastructure.persistence;

import org.example.agronomyexpert.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
