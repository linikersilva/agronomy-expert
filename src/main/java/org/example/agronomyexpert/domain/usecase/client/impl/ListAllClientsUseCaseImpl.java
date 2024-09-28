package org.example.agronomyexpert.domain.usecase.client.impl;

import org.example.agronomyexpert.domain.model.Client;
import org.example.agronomyexpert.domain.usecase.client.ListAllClientsUseCase;
import org.example.agronomyexpert.infrastructure.persistence.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListAllClientsUseCaseImpl implements ListAllClientsUseCase {

    private final ClientRepository clientRepository;

    public ListAllClientsUseCaseImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Page<Client> listAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }
}
