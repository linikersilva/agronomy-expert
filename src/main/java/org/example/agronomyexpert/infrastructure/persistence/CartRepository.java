package org.example.agronomyexpert.infrastructure.persistence;

import org.example.agronomyexpert.domain.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Modifying
    @Query(value = "UPDATE carrinho "
                 + "SET status = 'CANCELADO' "
                 + "WHERE id = :cartId", nativeQuery = true)
    void cancelCart(Integer cartId);
}
