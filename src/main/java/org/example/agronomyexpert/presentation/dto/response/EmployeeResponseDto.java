package org.example.agronomyexpert.presentation.dto.response;

import org.example.agronomyexpert.domain.model.enums.EmployeeStatusEnum;
import org.example.agronomyexpert.domain.model.enums.GenderEnum;

public record EmployeeResponseDto(Integer id,
                                  String createdAt,
                                  String name,
                                  String username,
                                  String password,
                                  String cpf,
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
                                  String birthdate,
                                  Integer roleId,
                                  EmployeeStatusEnum status) {}
