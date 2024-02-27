package com.example.school.api.repositories;

import com.example.school.api.dto.RecordPojo;
import com.example.school.api.entities.RecordEntity;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.*;
import java.util.*;

@Repository
@AllArgsConstructor
public class JdbcRecordRepository {
    private final JdbcTemplate jdbcTemplate;

    private RecordPojo recordMapper(ResultSet rs) throws SQLException {
        return RecordPojo.builder()
                .id(rs.getLong("id"))
                .date(rs.getDate("date"))
                .result(rs.getString("result").charAt(0))
                .subject(rs.getLong("subject_id"))
                .student(rs.getLong("student_id"))
                .build();
    }

    public Map<Long, ArrayList<RecordPojo>> getRecords(Long groupId, Long subjectId) throws SQLException {
        try (Connection conn = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT r.student_id, r.id, r.date, r.result, r.subject_id FROM records r INNER JOIN students s ON r.student_id = s.id WHERE s.group_id = ? AND r.subject_id = ?"
            );
            pstmt.setLong(1, groupId);
            pstmt.setLong(2, subjectId);
            ResultSet result = pstmt.executeQuery();
            Map<Long, ArrayList<RecordPojo>> records = new HashMap<>();
            while (result.next()) {
                Long studentId = result.getLong("student_id");
                if (records.containsKey(studentId)) {
                    ArrayList<RecordPojo> list = records.get(studentId);
                    list.add(recordMapper(result));
                }
                else {
                    ArrayList<RecordPojo> list = new ArrayList<>();
                    list.add(recordMapper(result));
                    records.put(studentId, list);
                }
            }
            return records;
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
            return null;
        }
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
