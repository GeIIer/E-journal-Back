package com.example.school.api.services;

import com.example.school.api.dto.SubjectPojo;
import com.example.school.api.dto.student.SubjectCountPojo;
import com.example.school.api.entities.SubjectEntity;
import com.example.school.api.mapper.BaseMapper;
import com.example.school.api.repositories.BaseRepository;
import com.example.school.api.repositories.SubjectRepository;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectService extends BaseEntityService<SubjectEntity, SubjectPojo> {
    public SubjectService(BaseRepository<SubjectEntity> repository,
                          BaseMapper<SubjectEntity, SubjectPojo> mapper) {
        super(repository, mapper);
    }

    @Transactional(readOnly = true)
    public List<SubjectCountPojo> getCountTeachers(Double salary) {
        return ((SubjectRepository) repository).countTeachers(salary)
                .stream()
                .map(this::getSubjectCountPojo)
                .toList();
    }

    private SubjectCountPojo getSubjectCountPojo(Tuple tuple) {
        return new SubjectCountPojo(
                tuple.get("id", Long.class),
                tuple.get("subject_name", String.class),
                tuple.get("study_hours", Integer.class),
                tuple.get("checkpoints", Integer.class),
                tuple.get("count", Long.class)
        );
    }
}
