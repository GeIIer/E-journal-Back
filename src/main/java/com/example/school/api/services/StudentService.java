package com.example.school.api.services;

import com.example.school.api.dto.StudentPojo;
import com.example.school.api.entities.StudentEntity;
import com.example.school.api.mapper.BaseMapper;
import com.example.school.api.repositories.BaseRepository;
import com.example.school.api.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService extends BaseEntityService<StudentEntity, StudentPojo> {
    public StudentService(BaseRepository<StudentEntity> repository,
                          BaseMapper<StudentEntity, StudentPojo> mapper) {
        super(repository, mapper);
    }

    public long count() {
        return repository.count();
    }

    public List<StudentPojo> findAllByGroup(Long groupId) {
        return ((StudentRepository) repository).findAllByGroup_Id(groupId)
                .stream()
                .map(mapper::fromEntity)
                .toList();
    }
}
