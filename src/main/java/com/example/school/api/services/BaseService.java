package com.example.school.api.services;

import java.util.List;

public interface BaseService<E, DTO> {
    List<DTO> findAll();

    DTO findById(Long id);

    DTO create(DTO obj);

    DTO update(DTO obj);

    Long deleteById(Long id);
}
