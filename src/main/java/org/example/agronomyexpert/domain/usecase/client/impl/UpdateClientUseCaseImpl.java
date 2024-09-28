package org.example.agronomyexpert.domain.usecase.client.impl;

import org.example.agronomyexpert.domain.exception.ClientNotFoundException;
import org.example.agronomyexpert.domain.model.Client;
import org.example.agronomyexpert.domain.usecase.client.UpdateClientUseCase;
import org.example.agronomyexpert.infrastructure.persistence.ClientRepository;
import org.example.agronomyexpert.presentation.dto.request.UpdateClientDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateClientUseCaseImpl implements UpdateClientUseCase {

    private final ClientRepository clientRepository;

    public UpdateClientUseCaseImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client updateClient(Integer id, UpdateClientDto updateClientDto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Não foi encontrado nenhum cliente com o id informado"));

        Optional.ofNullable(updateClientDto.name()).ifPresent(client::setName);
        Optional.ofNullable(updateClientDto.cpfCnpj()).ifPresent(client::setCpfCnpj);
        Optional.ofNullable(updateClientDto.phone()).ifPresent(client::setPhone);
        Optional.ofNullable(updateClientDto.ddd()).ifPresent(client::setDdd);
        Optional.ofNullable(updateClientDto.email()).ifPresent(client::setEmail);
        Optional.ofNullable(updateClientDto.street()).ifPresent(client::setStreet);
        Optional.ofNullable(updateClientDto.neighborhood()).ifPresent(client::setNeighborhood);
        Optional.ofNullable(updateClientDto.city()).ifPresent(client::setCity);
        Optional.ofNullable(updateClientDto.uf()).ifPresent(newUf -> client.setUf(newUf.toUpperCase()));
        Optional.ofNullable(updateClientDto.number()).ifPresent(client::setNumber);
        Optional.ofNullable(updateClientDto.cep()).ifPresent(client::setCep);
        Optional.ofNullable(updateClientDto.gender()).ifPresent(client::setGender);
        Optional.ofNullable(updateClientDto.birthdate()).ifPresent(client::setBirthdate);

        return clientRepository.save(client);
    }
}
