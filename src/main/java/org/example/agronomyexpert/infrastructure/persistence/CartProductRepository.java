package org.example.agronomyexpert.infrastructure.persistence;

import org.example.agronomyexpert.domain.model.CartProduct;
import org.example.agronomyexpert.domain.model.embedded.CartProductPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, CartProductPk> {

    List<CartProduct> findAllById_CartId_Id(Integer id);
}
