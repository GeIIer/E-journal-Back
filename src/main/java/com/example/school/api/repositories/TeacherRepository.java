package com.example.school.api.repositories;

import com.example.school.api.entities.TeacherEntity;
import jakarta.persistence.Tuple;
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

    @Query(value = "with recursive recurs as ( " +
            "SELECT " +
            "1 AS rank, " +
            "t.* " +
            "FROM teachers t " +
            "WHERE t.parent_id IS NULL " +
            "UNION " +
            "SELECT r.rank + 1 AS rank, " +
            "t.* " +
            "FROM teachers t INNER JOIN recurs r ON t.parent_id = r.parent_id )" +
            "SELECT recurs.*" +
            "FROM recurs " +
            "ORDER BY recurs.rank desc ",
            nativeQuery = true)
    List<Tuple> findAllWithRecourse();
}
