package com.example.school.api.repositories;

import com.example.school.api.entities.SubjectEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends BaseRepository<SubjectEntity> {
    @Query(value = "SELECT s.*, count(t.id) FROM subjects s " +
            "RIGHT JOIN teachers_subjects ts ON s.id = ts.subject_id " +
            "RIGHT JOIN teachers t ON ts.teacher_id = t.id " +
            "WHERE t.salary >= :salary " +
            "GROUP BY s.id " +
            "HAVING count(t.id) >= 1 ", nativeQuery = true)
    List<Tuple> countTeachers(@Param("salary") Double salary);
}