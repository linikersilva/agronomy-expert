package org.example.agronomyexpert.application;

import org.example.agronomyexpert.domain.model.Client;
import org.example.agronomyexpert.domain.usecase.client.CreateClientUseCase;
import org.example.agronomyexpert.domain.usecase.client.ListAllClientsUseCase;
import org.example.agronomyexpert.domain.usecase.client.UpdateClientUseCase;
import org.example.agronomyexpert.presentation.dto.request.CreateClientDto;
import org.example.agronomyexpert.presentation.dto.request.UpdateClientDto;
import org.example.agronomyexpert.presentation.dto.response.ClientResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

@Service
public class ClientService {

    private final CreateClientUseCase createClientUseCase;
    private final UpdateClientUseCase updateClientUseCase;
    private final ListAllClientsUseCase listAllClientsUseCase;

    public ClientService(CreateClientUseCase createClientUseCase,
                         UpdateClientUseCase updateClientUseCase,
                         ListAllClientsUseCase listAllClientsUseCase) {
        this.createClientUseCase = createClientUseCase;
        this.updateClientUseCase = updateClientUseCase;
        this.listAllClientsUseCase = listAllClientsUseCase;
    }

    @Transactional(readOnly = true)
    public Page<ClientResponseDto> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);

        return listAllClientsUseCase.listAll(pageable)
                .map(this::buildClientResponseDto);
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
