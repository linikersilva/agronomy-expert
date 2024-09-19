package org.example.agronomyexpert.presentation.controller;

import jakarta.validation.Valid;
import org.example.agronomyexpert.application.RoleService;
import org.example.agronomyexpert.presentation.dto.request.CreateRoleDto;
import org.example.agronomyexpert.presentation.dto.request.RoleResponseDto;
import org.example.agronomyexpert.presentation.dto.request.UpdateRoleDto;
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
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<Page<RoleResponseDto>> findAll(@RequestParam(defaultValue = "0") Integer page,
                                                         @RequestParam(defaultValue = "10") Integer size) {
        Page<RoleResponseDto> response = roleService.findAll(page, size);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<RoleResponseDto> createRole(@RequestBody @Valid CreateRoleDto createRoleDto) {
        RoleResponseDto role = roleService.createRole(createRoleDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(role.id()).toUri();
        return ResponseEntity.created(uri).body(role);
    }

    @PatchMapping("/{roleId}")
    public ResponseEntity<RoleResponseDto> updateRole(@RequestBody @Valid UpdateRoleDto updateRoleDto,
                                                      @PathVariable Integer roleId) {
        RoleResponseDto role = roleService.updateRole(roleId, updateRoleDto);
        return ResponseEntity.ok().body(role);
    }

}
