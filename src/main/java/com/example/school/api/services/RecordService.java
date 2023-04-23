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

import java.sql.SQLException;
import java.util.*;

@Service
@AllArgsConstructor
public class RecordService {
    private final JdbcRecordRepository jdbcRecordRepository;

    private final RecordRepository recordRepository;

    private final RecordMapper recordMapper;

    public Map<Long, ArrayList<RecordPojo>> getRecordsByGroupAndSubject(Long groupId, Long subjectId) throws SQLException {
        return jdbcRecordRepository.getRecords(groupId, subjectId);
    }

    public int[] saveAll(List<RecordPojo> recordPojo) throws SubjectNotFoundException, StudentNotFoundException{
        List<RecordEntity> records = recordPojo.stream().map(recordMapper::toEntity).toList();
        return jdbcRecordRepository.saveAll(records);
    }
}
