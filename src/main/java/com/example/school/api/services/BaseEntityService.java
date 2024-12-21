package com.example.school.api.services;

import com.example.school.api.mapper.BaseMapper;
import com.example.school.api.repositories.BaseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class BaseEntityService<E, DTO> implements BaseService<E, DTO> {
    protected final BaseRepository<E> repository;
    protected final BaseMapper<E, DTO> mapper;
    public BaseEntityService(BaseRepository<E> repository,
                       BaseMapper<E, DTO> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override
    @Transactional(readOnly = true)
    public List<DTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromEntity)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DTO findById(Long id) {
        Optional<E> optionalEntity = repository.findById(id);
        if (optionalEntity.isEmpty()) {
            throw new EntityNotFoundException("id = " + id);
        }
        return mapper.fromEntity(optionalEntity.get());
    }

    @Override
    @Transactional
    public DTO create(DTO obj) {
        final E entity = mapper.toEntity(obj);
        return (mapper.fromEntity(repository.save(entity)));
    }

    @Override
    @Transactional
    public DTO update(DTO obj) {
        final E entity = mapper.toEntity(obj);
        return (mapper.fromEntity(repository.save(entity)));
    }

    @Override
    @Transactional
    public Long deleteById(Long id) {
        Optional<E> deletedEntity = repository.findById(id);
        if (deletedEntity.isPresent()) {
            repository.deleteById(id);
            return id;
        }
        return null;
    }
}
