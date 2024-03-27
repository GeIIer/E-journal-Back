package com.example.school.api.mapper;

import com.example.school.api.dto.TeacherPojo;
import com.example.school.api.entities.TeacherEntity;
import com.example.school.api.repositories.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@AllArgsConstructor
public class TeacherMapper extends BaseMapper<TeacherEntity, TeacherPojo> {
    private final TeacherRepository teacherRepository;

    private final SubjectMapper subjectMapper;

    public TeacherPojo fromEntity (TeacherEntity entity){

        return TeacherPojo.builder()
                .id(entity.getId())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .email(entity.getEmail())
                .subjects(entity.getSubjects() != null ? entity.getSubjects()
                        .stream()
                        .map(subjectMapper::fromEntity)
                        .toList() : null)
                .experience(entity.getExperience())
                .salary(entity.getSalary())
                .parentId(entity.getParent().getId())
                .build();
    }

    public TeacherEntity toEntity (TeacherPojo pojo){
        TeacherEntity entity = new TeacherEntity();
        entity.setId(pojo.getId());
        entity.setFirstname(pojo.getFirstname());
        entity.setLastname(pojo.getLastname());
        entity.setEmail(pojo.getEmail());
        entity.setSubjects(pojo.getSubjects() != null ? pojo.getSubjects()
                .stream()
                .map(subjectMapper::toEntity)
                .toList() : null);
        if (pojo.getExperience() == null) {
            entity.setExperience(0);
        } else {
            entity.setExperience(pojo.getExperience());
        }
        if (pojo.getParentId() != null) {
            Optional<TeacherEntity> parent = teacherRepository.findById(entity.getId());
            parent.ifPresent(entity::setParent);
        }
        entity.setSalary(pojo.getSalary());
        return entity;
    }
}
