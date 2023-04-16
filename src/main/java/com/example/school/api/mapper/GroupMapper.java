package com.example.school.api.mapper;

import com.example.school.api.dto.GroupPojo;
import com.example.school.api.entities.GroupEntity;
import com.example.school.api.exceptions.TeacherNotFoundException;
import com.example.school.api.repositories.GroupRepository;
import com.example.school.api.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupMapper {
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;
    private final StudentMapper studentMapper;

    public GroupPojo fromEntity (GroupEntity entity){

        return GroupPojo.builder()
                .id(entity.getId())
                .classNumber(entity.getClassNumber())
                .classLetter(entity.getClassLetter())
                .teacherId(entity.getId())
                .listStudents(entity.getListStudents()
                        .stream()
                        .map(studentMapper::fromEntity)
                        .toList())
                .build();
    }

    public GroupEntity toEntity (GroupPojo pojo) throws TeacherNotFoundException {
        GroupEntity group = new GroupEntity();
        group.setId(pojo.getId());
        group.setClassNumber(pojo.getClassNumber());
        group.setClassLetter(pojo.getClassLetter());
        group.setTeacher(teacherRepository.findById(pojo.getTeacherId()).orElseThrow(
                () -> new TeacherNotFoundException(pojo.getTeacherId().toString())
        ));
        group.setListStudents(pojo.getListStudents()
                .stream()
                .map(studentMapper::toEntity)
                .toList());
        return group;
    }
}
