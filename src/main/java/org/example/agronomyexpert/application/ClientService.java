package org.example.agronomyexpert.application;

import org.example.agronomyexpert.domain.model.Client;
import org.example.agronomyexpert.domain.usecase.client.CreateClientUseCase;
import org.example.agronomyexpert.domain.usecase.client.UpdateClientUseCase;
import org.example.agronomyexpert.presentation.dto.request.CreateClientDto;
import org.example.agronomyexpert.presentation.dto.request.UpdateClientDto;
import org.example.agronomyexpert.presentation.dto.response.ClientResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

@Service
public class ClientService {

    private final CreateClientUseCase createClientUseCase;
    private final UpdateClientUseCase updateClientUseCase;

    public ClientService(CreateClientUseCase createClientUseCase,
                         UpdateClientUseCase updateClientUseCase) {
        this.createClientUseCase = createClientUseCase;
        this.updateClientUseCase = updateClientUseCase;
    }

    @Transactional
    public ClientResponseDto createClient(CreateClientDto createClientDto) {
        Client savedClient = createClientUseCase.createClient(createClientDto);

        return buildClientResponseDto(savedClient);
    }

    @Transactional
    public ClientResponseDto updateClient(Integer id, UpdateClientDto updateClientDto) {
        Client updatedClient = updateClientUseCase.updateClient(id, updateClientDto);

        return buildClientResponseDto(updatedClient);
    }

    private ClientResponseDto buildClientResponseDto(Client client) {
        return new ClientResponseDto(client.getId(),
                client.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                client.getName(),
                client.getCpfCnpj(),
                client.getPhone(),
                client.getDdd(),
                client.getEmail(),
                client.getStreet(),
                client.getNeighborhood(),
                client.getCity(),
                client.getUf(),
                client.getNumber(),
                client.getCep(),
                client.getGender(),
                client.getBirthdate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}
