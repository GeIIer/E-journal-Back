package com.example.school.api.mapper;

import com.example.school.api.dto.group.GroupPojo;
import com.example.school.api.dto.group.GroupWithoutStudentsPojo;
import com.example.school.api.entities.GroupEntity;
import com.example.school.api.exceptions.TeacherNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GroupMapper extends BaseMapper<GroupEntity, GroupPojo> {
    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;

    public GroupPojo fromEntity (GroupEntity entity) {

        return GroupPojo.builder()
                .id(entity.getId())
                .classNumber(entity.getClassNumber())
                .classLetter(entity.getClassLetter())
                .teacher(teacherMapper.fromEntity(entity.getTeacher()))
                .listStudents(entity.getListStudents() != null ? entity.getListStudents()
                        .stream()
                        .map(studentMapper::fromEntity)
                        .toList(): null)
                .build();
    }

    public GroupWithoutStudentsPojo fromEntityWithoutStudents (GroupEntity entity) {

        return GroupWithoutStudentsPojo.builder()
                .id(entity.getId())
                .classNumber(entity.getClassNumber())
                .classLetter(entity.getClassLetter())
                .teacher(teacherMapper.fromEntity(entity.getTeacher()))
                .build();
    }

    public GroupEntity toEntity (GroupPojo pojo) throws TeacherNotFoundException {
        GroupEntity group = new GroupEntity();
        group.setId(pojo.getId());
        group.setClassNumber(pojo.getClassNumber());
        group.setClassLetter(pojo.getClassLetter());
        group.setTeacher(teacherMapper.toEntity(pojo.getTeacher()));
        group.setListStudents(pojo.getListStudents() != null ? pojo.getListStudents()
                .stream()
                .map(studentMapper::toEntity)
                .toList() : null);
        return group;
    }
}
