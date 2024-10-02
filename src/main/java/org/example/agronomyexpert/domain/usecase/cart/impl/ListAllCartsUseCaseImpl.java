package org.example.agronomyexpert.domain.usecase.cart.impl;

import org.example.agronomyexpert.domain.exception.EmployeeNotFoundException;
import org.example.agronomyexpert.domain.model.Cart;
import org.example.agronomyexpert.domain.model.Employee;
import org.example.agronomyexpert.domain.model.enums.AccessLevelEnum;
import org.example.agronomyexpert.domain.usecase.cart.ListAllCartsUseCase;
import org.example.agronomyexpert.infrastructure.persistence.CartRepository;
import org.example.agronomyexpert.infrastructure.persistence.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListAllCartsUseCaseImpl implements ListAllCartsUseCase {

    private final CartRepository cartRepository;
    private final EmployeeRepository employeeRepository;

    public ListAllCartsUseCaseImpl(CartRepository cartRepository,
                                   EmployeeRepository employeeRepository) {
        this.cartRepository = cartRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Page<Cart> listAll(String requesterUsername, Pageable pageable) {
        Employee requester = employeeRepository.findByUsername(requesterUsername)
                .orElseThrow(() -> new EmployeeNotFoundException("Não foi possível identificar o responsável pela request"));

        Page<Cart> allCarts;
        if (AccessLevelEnum.BASICO.equals(requester.getRoleFk().getAccessLevel())) {
            allCarts = cartRepository.findAllBySellerFk_Id(requester.getId(), pageable);
        } else {
            allCarts = cartRepository.findAll(pageable);
        }

        return allCarts;
    }
}
