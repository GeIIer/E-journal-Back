package com.example.school.api.services;

import com.example.school.api.dto.TeacherPojo;
import com.example.school.api.dto.TeacherPojoWithRank;
import com.example.school.api.entities.TeacherEntity;
import com.example.school.api.mapper.BaseMapper;
import com.example.school.api.repositories.BaseRepository;
import com.example.school.api.repositories.TeacherRepository;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService extends BaseEntityService<TeacherEntity, TeacherPojo> {
    public TeacherService(BaseRepository<TeacherEntity> repository,
                          BaseMapper<TeacherEntity, TeacherPojo> mapper) {
        super(repository, mapper);
    }

    public List<TeacherPojo> findAllBySubjects(List<Long> subjectsIds) {
        List<TeacherEntity> result = ((TeacherRepository)repository).findAllBySubjectsIds(subjectsIds);
        return result.stream()
                .map(mapper::fromEntity)
                .toList();
    }

    public List<TeacherPojoWithRank> findAllWithRecourse() {
        return ((TeacherRepository) repository).findAllWithRecourse()
                .stream()
                .map(this::getTeacherPojoWithRank)
                .toList();
    }

    private TeacherPojoWithRank getTeacherPojoWithRank(Tuple tuple) {
        return new TeacherPojoWithRank(
                tuple.get("id", Long.class),
                tuple.get("firstname", String.class),
                tuple.get("lastname", String.class),
                tuple.get("email", String.class),
                tuple.get("experience", Integer.class),
                tuple.get("salary", Double.class),
                tuple.get("parent_id", Long.class),
                tuple.get("rank", Integer.class)
        );
    }
}
