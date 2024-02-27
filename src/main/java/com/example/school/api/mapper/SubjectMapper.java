package com.example.school.api.mapper;

import com.example.school.api.dto.SubjectPojo;
import com.example.school.api.entities.SubjectEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SubjectMapper extends BaseMapper<SubjectEntity, SubjectPojo> {
    public SubjectPojo fromEntity (SubjectEntity entity){

        return SubjectPojo.builder()
                .id(entity.getId())
                .subjectName(entity.getSubjectName())
                .checkpoints(entity.getCheckpoints())
                .studyHours(entity.getStudyHours())
                .build();
    }

    public SubjectEntity toEntity (SubjectPojo pojo) {
        SubjectEntity entity = new SubjectEntity();
        entity.setId(pojo.getId());
        entity.setSubjectName(pojo.getSubjectName());
        entity.setStudyHours(pojo.getStudyHours());
        entity.setCheckpoints(pojo.getCheckpoints());
        return entity;
    }
}
