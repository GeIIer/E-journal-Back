package com.example.school.api.mapper;

import com.example.school.api.dto.TeacherPojo;
import com.example.school.api.entities.TeacherEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class TeacherMapper {

    private final SubjectMapper subjectMapper;

    public TeacherPojo fromEntity (TeacherEntity entity){

        return TeacherPojo.builder()
                .id(entity.getId())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .email(entity.getEmail())
                .subjects(entity.getSubjects()
                        .stream()
                        .map(subjectMapper::fromEntity)
                        .toList())
                .experience(entity.getExperience())
                .salary(entity.getSalary())
                .build();
    }

    public TeacherEntity toEntity (TeacherPojo pojo){
        TeacherEntity entity = new TeacherEntity();
        entity.setId(pojo.getId());
        entity.setFirstname(pojo.getFirstname());
        entity.setLastname(pojo.getLastname());
        entity.setEmail(pojo.getEmail());
        entity.setSubjects(pojo.getSubjects()
                .stream()
                .map(subjectMapper::toEntity)
                .toList());
        entity.setExperience(pojo.getExperience());
        entity.setSalary(pojo.getSalary());
        return entity;
    }
}
