package com.example.school.api.mapper;

import com.example.school.api.dto.StudentPojo;
import com.example.school.api.entities.StudentEntity;
import com.example.school.api.repositories.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentMapper {

    private final GroupRepository groupRepository;

    public StudentPojo fromEntity (StudentEntity entity){

        return StudentPojo.builder()
                .id(entity.getId())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .email(entity.getEmail())
                .groupId(entity.getGroup().getId())
                .build();
    }

    public StudentEntity toEntity (StudentPojo pojo) {
        StudentEntity student = new StudentEntity();
        student.setId(pojo.getId());
        student.setFirstname(pojo.getFirstname());
        student.setLastname(pojo.getLastname());
        student.setEmail(pojo.getEmail());
        student.setGroup(groupRepository.findById(pojo.getId()).orElse(null));
        return student;
    }
}
