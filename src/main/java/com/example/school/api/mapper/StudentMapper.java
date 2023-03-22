package com.example.school.api.mapper;

import com.example.school.api.dto.StudentPojo;
import com.example.school.api.entities.StudentEntity;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public StudentPojo fromEntity (StudentEntity entity){

        return StudentPojo.builder()
                .id(entity.getId())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .email(entity.getEmail())
                .groupId(entity.getGroup().getId())
                .build();
    }
}
