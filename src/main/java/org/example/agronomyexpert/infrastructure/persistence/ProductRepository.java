package org.example.agronomyexpert.infrastructure.persistence;

import org.example.agronomyexpert.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Modifying
    @Query(value = "UPDATE produto "
                 + "SET quantidade_em_estoque_atual = quantidade_em_estoque_atual + :quantityToBeAdded "
                 + "WHERE id = :productId", nativeQuery = true)
    void increaseProductQuantity(Integer quantityToBeAdded,
                                 Integer productId);

    @Modifying
    @Query(value = "UPDATE produto "
                 + "SET quantidade_em_estoque_atual = quantidade_em_estoque_atual - :quantityToBeRemoved "
                 + "WHERE id = :productId", nativeQuery = true)
    void decreaseProductQuantity(Integer quantityToBeRemoved,
                                 Integer productId);
}
