package org.example.agronomyexpert.domain.usecase.cart.impl;

import org.example.agronomyexpert.domain.exception.CartNotFoundException;
import org.example.agronomyexpert.domain.exception.EmployeeNotFoundException;
import org.example.agronomyexpert.domain.model.Cart;
import org.example.agronomyexpert.domain.model.Employee;
import org.example.agronomyexpert.domain.model.enums.AccessLevelEnum;
import org.example.agronomyexpert.domain.usecase.cart.ResumeCartUseCase;
import org.example.agronomyexpert.infrastructure.persistence.CartRepository;
import org.example.agronomyexpert.infrastructure.persistence.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class ResumeCartUseCaseImpl implements ResumeCartUseCase {

    private final CartRepository cartRepository;
    private final EmployeeRepository employeeRepository;

    public ResumeCartUseCaseImpl(CartRepository cartRepository,
                                 EmployeeRepository employeeRepository) {
        this.cartRepository = cartRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Cart resumeCart(String requesterUsername, Integer cartId) {
        Employee requester = employeeRepository.findByUsername(requesterUsername)
                .orElseThrow(() -> new EmployeeNotFoundException("Não foi possível identificar o responsável pela request"));

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Não foi encontrado nenhum carrinho com o id informado"));

        if (AccessLevelEnum.BASICO.equals(requester.getRoleFk().getAccessLevel()) && !cart.getSellerFk().equals(requester)) {
            throw new IllegalArgumentException("Você não tem permissão para retomar carrinhos de outros funcionários");
        }

        return cart;
    }
}
