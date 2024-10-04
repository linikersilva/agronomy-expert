package org.example.agronomyexpert.domain.usecase.cart.impl;

import org.example.agronomyexpert.domain.exception.CartNotFoundException;
import org.example.agronomyexpert.domain.exception.EmployeeNotFoundException;
import org.example.agronomyexpert.domain.exception.ProductNotFoundException;
import org.example.agronomyexpert.domain.model.Cart;
import org.example.agronomyexpert.domain.model.CartProduct;
import org.example.agronomyexpert.domain.model.Employee;
import org.example.agronomyexpert.domain.model.Product;
import org.example.agronomyexpert.domain.model.Stock;
import org.example.agronomyexpert.domain.model.enums.AccessLevelEnum;
import org.example.agronomyexpert.domain.model.enums.CartStatusEnum;
import org.example.agronomyexpert.domain.model.enums.StockOperationTypeEnum;
import org.example.agronomyexpert.domain.usecase.cart.DeleteCartUseCase;
import org.example.agronomyexpert.infrastructure.persistence.CartProductRepository;
import org.example.agronomyexpert.infrastructure.persistence.CartRepository;
import org.example.agronomyexpert.infrastructure.persistence.EmployeeRepository;
import org.example.agronomyexpert.infrastructure.persistence.ProductRepository;
import org.example.agronomyexpert.infrastructure.persistence.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteCartUseCaseImpl implements DeleteCartUseCase {

    private final CartRepository cartRepository;
    private final EmployeeRepository employeeRepository;
    private final CartProductRepository cartProductRepository;
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;

    public DeleteCartUseCaseImpl(CartRepository cartRepository,
                                 EmployeeRepository employeeRepository,
                                 CartProductRepository cartProductRepository,
                                 StockRepository stockRepository,
                                 ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.employeeRepository = employeeRepository;
        this.cartProductRepository = cartProductRepository;
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Cart deleteCart(String requesterUsername, Integer cartId) {
        Employee requester = employeeRepository.findByUsername(requesterUsername)
                .orElseThrow(() -> new EmployeeNotFoundException("Não foi possível identificar o responsável pela request"));

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Não foi encontrado nenhum carrinho com o id informado"));

        if (AccessLevelEnum.BASICO.equals(requester.getRoleFk().getAccessLevel()) && !cart.getSellerFk().equals(requester)) {
            throw new IllegalArgumentException("Você não tem permissão para cancelar carrinhos de outros funcionários");
        }

        List<CartProduct> cartProducts = cartProductRepository.findAllById_CartId_Id(cartId);
        cartProducts.forEach(cartProduct -> {
            Integer productId = cartProduct.getId().getProductId().getId();

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException("Não foi encontrado nenhum produto com o id informado"));

            stockRepository.save(Stock.create(product, cartProduct.getQuantity(), StockOperationTypeEnum.ENTRADA));

            productRepository.increaseProductQuantity(cartProduct.getQuantity(), productId);
        });

        cart.setStatus(CartStatusEnum.CANCELADO);
        cartRepository.save(cart);

        return cart;
    }
}
