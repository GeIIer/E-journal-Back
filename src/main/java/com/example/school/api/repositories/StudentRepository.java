package com.example.school.api.repositories;

import com.example.school.api.entities.StudentEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends BaseRepository<StudentEntity> {
    @Query(value = "SELECT row_number() OVER (ORDER BY s.lastname DESC) AS orderNum, s.* " +
            "FROM students s " +
            "WHERE s.group_id = :groupId ", nativeQuery = true)
    List<Tuple> findAllByGroupId(@Param("groupId") Long groupId);

    @Query(value = "SELECT s.*, s2.subject_name, avg(r.\"result\") FROM records r " +
            "LEFT JOIN students s ON s.id = r.student_id " +
            "FULL JOIN subjects s2 ON r.subject_id = s2.id " +
            "GROUP BY s.id, r.subject_id, s2.subject_name ", nativeQuery = true)
    List<Tuple> findAverageById(@Param("id") Long id);

    @Query(value = "SELECT s.*, avg_detailed.subject_name, avg_detailed.avg_result AS \"avg\" " +
            "FROM students s " +
            "CROSS JOIN ( " +
            "    SELECT s2.subject_name, r.student_id, avg(r.result) AS avg_result " +
            "    FROM records r " +
            "    CROSS JOIN subjects s2 " +
            "    WHERE s2.id = r.subject_id " +
            "    GROUP BY r.student_id, s2.subject_name " +
            ") avg_detailed " +
            "WHERE s.id = avg_detailed.student_id " +
            "ORDER BY s.id ", nativeQuery = true)
    List<Tuple> findAverageCrossById();
}