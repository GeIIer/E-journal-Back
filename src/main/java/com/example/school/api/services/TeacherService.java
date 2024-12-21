package com.example.school.api.services;

import com.example.school.api.dto.TeacherPojo;
import com.example.school.api.entities.TeacherEntity;
import com.example.school.api.mapper.BaseMapper;
import com.example.school.api.repositories.BaseRepository;
import com.example.school.api.repositories.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherService extends BaseEntityService<TeacherEntity, TeacherPojo> {
    public TeacherService(BaseRepository<TeacherEntity> repository,
                          BaseMapper<TeacherEntity, TeacherPojo> mapper) {
        super(repository, mapper);
    }

    @Transactional(readOnly = true)
    public List<TeacherPojo> findAllBySubjects(List<Long> subjectsIds) {
        List<TeacherEntity> result = ((TeacherRepository)repository).findAllBySubjectsIds(subjectsIds);
        return result.stream()
                .map(mapper::fromEntity)
                .toList();
    }
}
