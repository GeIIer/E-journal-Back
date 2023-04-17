package com.example.school.api.services;

import com.example.school.api.dto.RecordPojo;
import com.example.school.api.entities.RecordEntity;
import com.example.school.api.exceptions.StudentNotFoundException;
import com.example.school.api.exceptions.SubjectNotFoundException;
import com.example.school.api.mapper.RecordMapper;
import com.example.school.api.repositories.JdbcRecordRepository;
import com.example.school.api.repositories.RecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
@AllArgsConstructor
public class RecordService {
    private final JdbcRecordRepository jdbcRecordRepository;

    private final RecordRepository recordRepository;

    private final RecordMapper recordMapper;

    private RecordPojo recordMapper(ResultSet rs) throws SQLException {
        return RecordPojo.builder()
                .id(rs.getLong("id"))
                .date(rs.getDate("date"))
                .result(rs.getString("result").charAt(0))
                .subject(rs.getLong("subject_id"))
                .student(rs.getLong("student_id"))
                .build();
    }

    public Map<Long, ArrayList<RecordPojo>> getRecordsByGroupAndSubject(Long groupId, Long subjectId) throws SQLException {

        ResultSet result = jdbcRecordRepository.getRecords(groupId, subjectId);
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

    public int[] saveAll(List<RecordPojo> recordPojo) throws SubjectNotFoundException, StudentNotFoundException{
        List<RecordEntity> records = recordPojo.stream().map(recordMapper::toEntity).toList();
        return jdbcRecordRepository.saveAll(records);
    }
}
