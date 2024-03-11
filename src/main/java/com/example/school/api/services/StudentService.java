package com.example.school.api.services;

import com.example.school.api.dto.student.StudentAveragePojo;
import com.example.school.api.dto.student.StudentOrderPojo;
import com.example.school.api.dto.student.StudentPojo;
import com.example.school.api.entities.StudentEntity;
import com.example.school.api.mapper.BaseMapper;
import com.example.school.api.repositories.BaseRepository;
import com.example.school.api.repositories.StudentRepository;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public List<StudentOrderPojo> findAllByGroup(Long groupId) {
        return ((StudentRepository) repository).findAllByGroupId(groupId)
                .stream()
                .map(this::getStudentOrderPojo)
                .toList();
    }

    public List<StudentAveragePojo> findAverageMarkByStudent() {
        return ((StudentRepository) repository).findAverageCrossById()
                .stream()
                .map(this::getStudentAveragePojo)
                .toList();
    }

    private StudentAveragePojo getStudentAveragePojo(Tuple tuple) {
        return new StudentAveragePojo(
                tuple.get("id", Long.class),
                tuple.get("firstname", String.class),
                tuple.get("lastname", String.class),
                tuple.get("email", String.class),
                tuple.get("group_id", Long.class),
                tuple.get("subject_name", String.class),
                tuple.get("avg", BigDecimal.class)
        );
    }

    private StudentOrderPojo getStudentOrderPojo(Tuple tuple) {
        return new StudentOrderPojo(
                tuple.get("id", Long.class),
                tuple.get("firstname", String.class),
                tuple.get("lastname", String.class),
                tuple.get("email", String.class),
                tuple.get("group_id", Long.class),
                tuple.get("ordernum", Long.class)
        );
    }
}
