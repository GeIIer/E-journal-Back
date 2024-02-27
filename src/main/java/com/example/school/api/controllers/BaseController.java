package com.example.school.api.controllers;

import com.example.school.api.services.BaseService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class BaseController<E, DTO> implements CommonController<E, DTO> {
    protected final BaseService<E, DTO> service;

    public BaseController(BaseService<E, DTO> service) {
        this.service = service;
    }

    @Override
    @Operation(summary = "Получить все сущности")
    public List<DTO> getAll() {
        return service.findAll();
    }

    @Override
    @Operation(summary = "Получить сущность по Id")
    public DTO getById(Long id) {
        return service.findById(id);
    }

    @Override
    @Operation(summary = "Создать сущность")
    public DTO create(DTO obj) {
        return service.create(obj);
    }

    @Override
    @Operation(summary = "Изменить сущность")
    public DTO updateById(DTO obj) {
        return service.update(obj);
    }

    @Override
    @Operation(summary = "Удалить сущность по Id")
    public ResponseEntity<String> deleteById(Long id) {
        return new ResponseEntity<>(String.valueOf(service.deleteById(id)), HttpStatus.OK);
    }
}
