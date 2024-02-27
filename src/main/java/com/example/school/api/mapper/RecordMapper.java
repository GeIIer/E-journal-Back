package com.example.school.api.mapper;

import com.example.school.api.dto.RecordPojo;
import com.example.school.api.entities.RecordEntity;
import com.example.school.api.exceptions.StudentNotFoundException;
import com.example.school.api.exceptions.SubjectNotFoundException;
import com.example.school.api.repositories.StudentRepository;
import com.example.school.api.repositories.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RecordMapper extends BaseMapper<RecordEntity, RecordPojo> {

    private final StudentRepository studentRepository;

    private final SubjectRepository subjectRepository;

    public RecordPojo fromEntity (RecordEntity entity){

        return RecordPojo.builder()
                .id(entity.getId())
                .student(entity.getStudent().getId())
                .date(entity.getDate())
                .subject(entity.getSubject().getId())
                .result(entity.getResult())
                .build();
    }

    public RecordEntity toEntity (RecordPojo pojo) throws SubjectNotFoundException, StudentNotFoundException {
        RecordEntity record = new RecordEntity();
        record.setId(pojo.getId());
        record.setDate(pojo.getDate());
        record.setSubject(subjectRepository.findById(pojo.getSubject()).orElseThrow(
                () -> new SubjectNotFoundException(pojo.getSubject().toString())
        ));
        record.setStudent(studentRepository.findById(pojo.getStudent()).orElseThrow(
                () -> new StudentNotFoundException(pojo.getStudent().toString())
        ));
        record.setResult(pojo.getResult());
        return record;
    }
}
