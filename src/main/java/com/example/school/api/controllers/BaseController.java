package com.example.school.api.controllers;

import com.example.school.api.services.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class BaseController<E, DTO> implements CommonController<E, DTO> {
    protected final BaseService<E, DTO> service;

    public BaseController(BaseService<E, DTO> service) {
        this.service = service;
    }

    @Override
    public List<DTO> getAll() {
        return service.findAll();
    }

    @Override
    public DTO getById(Long id) {
        return service.findById(id);
    }

    @Override
    public DTO create(DTO obj) {
        return service.create(obj);
    }

    @Override
    public DTO updateById(DTO obj) {
        return service.update(obj);
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        return new ResponseEntity<>(String.valueOf(service.deleteById(id)), HttpStatus.OK);
    }
}
