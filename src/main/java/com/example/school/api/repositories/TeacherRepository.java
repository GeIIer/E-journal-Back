package com.example.school.api.repositories;

import com.example.school.api.entities.TeacherEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends BaseRepository<TeacherEntity> {
    @Query(value = "SELECT DISTINCT t.* FROM teachers t " +
            "LEFT JOIN teachers_subjects ts ON t.id = ts.teacher_id " +
            "LEFT JOIN subjects s ON ts.subject_id = s.id " +
            "WHERE subject_id IN :ids", nativeQuery = true)
    List<TeacherEntity> findAllBySubjectsIds(@Param("ids") List<Long> ids);
}
