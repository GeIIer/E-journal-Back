package com.example.school.api.repositories;

import com.example.school.api.entities.RecordEntity;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class JdbcRecordRepository {
    private final JdbcTemplate jdbcTemplate;

    public ResultSet getRecords(Long groupId, Long subjectId) throws SQLException {
        PreparedStatement pstmt = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection().prepareStatement(
                "SELECT r.student_id, r.id, r.date, r.result, r.subject_id FROM records r INNER JOIN students s ON r.student_id = s.id WHERE s.group_id = ? AND r.subject_id = ?"
        );
        pstmt.setLong(1, groupId);
        pstmt.setLong(2, subjectId);
        return pstmt.executeQuery();
    }

    //TODO: исправить баг
    @Transactional
    public int[] saveAll(List<RecordEntity> arrayList) {
        List<Object[]> values = new ArrayList<>();
        for (RecordEntity record: arrayList) {
            java.sql.Date date = new Date(record.getDate().getTime());
            Object[] item = new Object[] {
                    record.getId(), date, record.getResult(), record.getStudent().getId(), record.getSubject().getId()
            };
            values.add(item);
        }
        return jdbcTemplate.batchUpdate("INSERT INTO records(id, date, result, student_id, subject_id) VALUES (COALESCE(?, nextval('records_id_seq')), ?, ?, ?, ?)" +
                        "ON CONFLICT (id) DO UPDATE SET date = excluded.date, result = excluded.result, student_id = excluded.student_id, subject_id = excluded.subject_id",
                values);
    }
}
