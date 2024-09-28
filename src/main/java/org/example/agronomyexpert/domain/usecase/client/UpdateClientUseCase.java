package org.example.agronomyexpert.domain.usecase.client;

import org.example.agronomyexpert.domain.model.Client;
import org.example.agronomyexpert.presentation.dto.request.UpdateClientDto;

public interface UpdateClientUseCase {
    Client updateClient(Integer id, UpdateClientDto updateClientDto);
}
