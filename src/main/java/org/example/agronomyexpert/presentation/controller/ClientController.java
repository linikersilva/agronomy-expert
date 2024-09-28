package org.example.agronomyexpert.presentation.controller;

import jakarta.validation.Valid;
import org.example.agronomyexpert.application.ClientService;
import org.example.agronomyexpert.presentation.dto.request.CreateClientDto;
import org.example.agronomyexpert.presentation.dto.request.UpdateClientDto;
import org.example.agronomyexpert.presentation.dto.response.ClientResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<Page<ClientResponseDto>> findAll(@RequestParam(defaultValue = "0") Integer page,
                                                             @RequestParam(defaultValue = "10") Integer size) {
        Page<ClientResponseDto> response = clientService.findAll(page, size);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<ClientResponseDto> createClient(@RequestBody @Valid CreateClientDto createClientDto) {
        ClientResponseDto client = clientService.createClient(createClientDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(client.id()).toUri();
        return ResponseEntity.created(uri).body(client);
    }

    @PatchMapping("/{clientId}")
    public ResponseEntity<ClientResponseDto> updateClient(@RequestBody @Valid UpdateClientDto updateClientDto,
                                                          @PathVariable Integer clientId) {
        ClientResponseDto client = clientService.updateClient(clientId, updateClientDto);
        return ResponseEntity.ok().body(client);
    }
}
