package com.example.school.api.services;

import com.example.school.api.dto.RecordPojo;
import com.example.school.api.entities.RecordEntity;
import com.example.school.api.exceptions.StudentNotFoundException;
import com.example.school.api.exceptions.SubjectNotFoundException;
import com.example.school.api.mapper.BaseMapper;
import com.example.school.api.repositories.BaseRepository;
import com.example.school.api.repositories.JdbcRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RecordService extends BaseEntityService<RecordEntity, RecordPojo> {
    private final JdbcRecordRepository jdbcRecordRepository;
    public RecordService(BaseRepository<RecordEntity> repository,
                         JdbcRecordRepository jdbcRecordRepository,
                         BaseMapper<RecordEntity, RecordPojo> mapper) {
        super(repository, mapper);
        this.jdbcRecordRepository = jdbcRecordRepository;
    }

    @Transactional
    public Map<Long, ArrayList<RecordPojo>> getRecordsByGroupAndSubject(Long groupId, Long subjectId) throws SQLException {
        return jdbcRecordRepository.getRecords(groupId, subjectId);
    }

    @Transactional()
    public int[] saveAll(List<RecordPojo> recordPojo) throws SubjectNotFoundException, StudentNotFoundException{
        List<RecordEntity> records = recordPojo.stream().map(mapper::toEntity).toList();
        return jdbcRecordRepository.saveAll(records);
    }
}
