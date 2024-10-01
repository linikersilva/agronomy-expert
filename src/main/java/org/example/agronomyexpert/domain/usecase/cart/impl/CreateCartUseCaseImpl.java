package org.example.agronomyexpert.domain.usecase.cart.impl;

import org.example.agronomyexpert.domain.exception.ClientNotFoundException;
import org.example.agronomyexpert.domain.exception.EmployeeNotFoundException;
import org.example.agronomyexpert.domain.model.Cart;
import org.example.agronomyexpert.domain.model.Client;
import org.example.agronomyexpert.domain.model.Employee;
import org.example.agronomyexpert.domain.model.enums.AccessLevelEnum;
import org.example.agronomyexpert.domain.usecase.cart.CreateCartUseCase;
import org.example.agronomyexpert.infrastructure.persistence.CartRepository;
import org.example.agronomyexpert.infrastructure.persistence.ClientRepository;
import org.example.agronomyexpert.infrastructure.persistence.EmployeeRepository;
import org.example.agronomyexpert.presentation.dto.request.CreateCartDto;
import org.springframework.stereotype.Service;

@Service
public class CreateCartUseCaseImpl implements CreateCartUseCase {

    private final CartRepository cartRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    public CreateCartUseCaseImpl(CartRepository cartRepository,
                                 ClientRepository clientRepository,
                                 EmployeeRepository employeeRepository) {
        this.cartRepository = cartRepository;
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Cart createCart(String requesterUsername, CreateCartDto createCartDto) {
        Employee requester = employeeRepository.findByUsername(requesterUsername)
                .orElseThrow(() -> new EmployeeNotFoundException("Não foi possível identificar o responsável pela request"));

        if (AccessLevelEnum.BASICO.equals(requester.getRoleFk().getAccessLevel()) && createCartDto.sellerId() != null) {
            throw new IllegalArgumentException("Você não tem permissão para criar carrinhos para outros funcionários");
        }

        Employee seller = requester;
        if (createCartDto.sellerId() != null) {
            seller = employeeRepository.findById(createCartDto.sellerId())
                    .orElseThrow(() -> new EmployeeNotFoundException("Não foi encontrado nenhum funcionário com o id informado"));
        }

        Client client = clientRepository.findById(createCartDto.clientId())
                .orElseThrow(() -> new ClientNotFoundException("Não foi encontrado nenhum cliente com o id informado"));

        Cart cart = Cart.create(client, seller);

        return cartRepository.save(cart);
    }
}
