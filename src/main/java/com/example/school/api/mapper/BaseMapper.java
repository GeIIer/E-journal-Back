package com.example.school.api.mapper;

public abstract class BaseMapper<E, DTO> {
    public abstract DTO fromEntity(E entity);
    public abstract E toEntity(DTO dto);
}
