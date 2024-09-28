package org.example.agronomyexpert.presentation.dto.response;

import org.example.agronomyexpert.domain.model.enums.GenderEnum;

public record ClientResponseDto(Integer id,
                                String createdAt,
                                String name,
                                String cpfCnpj,
                                String phone,
                                String ddd,
                                String email,
                                String street,
                                String neighborhood,
                                String city,
                                String uf,
                                String number,
                                String cep,
                                GenderEnum gender,
                                String birthdate) {}
