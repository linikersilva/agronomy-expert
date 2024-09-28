package org.example.agronomyexpert.domain.usecase.client;

import org.example.agronomyexpert.domain.model.Client;
import org.example.agronomyexpert.presentation.dto.request.CreateClientDto;

public interface CreateClientUseCase {
    Client createClient(CreateClientDto createClientDto);
}
