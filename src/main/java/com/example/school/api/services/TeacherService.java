package com.example.school.api.services;

import com.example.school.api.dto.TeacherPojo;
import com.example.school.api.entities.TeacherEntity;
import com.example.school.api.mapper.BaseMapper;
import com.example.school.api.repositories.BaseRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherService extends BaseEntityService<TeacherEntity, TeacherPojo> {
    public TeacherService(BaseRepository<TeacherEntity> repository,
                          BaseMapper<TeacherEntity, TeacherPojo> mapper) {
        super(repository, mapper);
    }
}
