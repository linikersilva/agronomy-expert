package org.example.agronomyexpert.infrastructure.persistence;

import org.example.agronomyexpert.domain.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
}
