package org.example.agronomyexpert.domain.usecase.client.impl;

import org.example.agronomyexpert.domain.model.Client;
import org.example.agronomyexpert.domain.usecase.client.CreateClientUseCase;
import org.example.agronomyexpert.infrastructure.persistence.ClientRepository;
import org.example.agronomyexpert.presentation.dto.request.CreateClientDto;
import org.springframework.stereotype.Service;

@Service
public class CreateClientUseCaseImpl implements CreateClientUseCase {

    private final ClientRepository clientRepository;

    public CreateClientUseCaseImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client createClient(CreateClientDto createClientDto) {
        Client client = Client.create(createClientDto);
        return clientRepository.save(client);
    }
}
