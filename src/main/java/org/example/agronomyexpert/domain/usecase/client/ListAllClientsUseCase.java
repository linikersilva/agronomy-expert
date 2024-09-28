package org.example.agronomyexpert.domain.usecase.client;

import org.example.agronomyexpert.domain.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListAllClientsUseCase {
    Page<Client> listAll(Pageable pageable);
}
