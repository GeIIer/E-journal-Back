package com.example.school.api.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CommonController<E, DTO> {
    @GetMapping()
    List<DTO> getAll();

    @GetMapping(value = "/{id}")
    DTO getById(@PathVariable Long id);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    DTO create(final @RequestBody DTO obj);

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    DTO updateById(final @RequestBody DTO obj);

    @DeleteMapping(value = "/{id}")
    ResponseEntity<String> deleteById(@PathVariable Long id);
}
