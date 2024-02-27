package com.example.school.api.services;

import com.example.school.api.dto.SubjectPojo;
import com.example.school.api.dto.group.GroupPojo;
import com.example.school.api.dto.group.GroupWithoutStudentsPojo;
import com.example.school.api.dto.group.GroupsAndSubjectsPojo;
import com.example.school.api.entities.GroupEntity;
import com.example.school.api.exceptions.GroupNotFoundException;
import com.example.school.api.mapper.BaseMapper;
import com.example.school.api.mapper.TeacherMapper;
import com.example.school.api.repositories.BaseRepository;
import com.example.school.api.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService extends BaseEntityService<GroupEntity, GroupPojo> {
    private final SubjectService subjectService;
    private final TeacherMapper teacherMapper;
    public GroupService(BaseRepository<GroupEntity> repository,
                        BaseMapper<GroupEntity, GroupPojo> mapper,
                        SubjectService subjectService, TeacherMapper teacherMapper) {
        super(repository, mapper);
        this.subjectService = subjectService;
        this.teacherMapper = teacherMapper;
    }

    public GroupPojo findByName(Character letter) {
        Optional<GroupEntity> group = ((GroupRepository) repository).findByClassLetter(letter);
        return group.map(mapper::fromEntity).orElseThrow(
                () -> new GroupNotFoundException(letter.toString()));
    }

    public GroupsAndSubjectsPojo findAllGroupsAndSubjects() {
        List<GroupWithoutStudentsPojo> groupsPojo = repository.findAll().stream().map(group -> {
            GroupWithoutStudentsPojo groupPojo = new GroupWithoutStudentsPojo();
            groupPojo.setId(group.getId());
            groupPojo.setClassNumber(group.getClassNumber());
            groupPojo.setClassLetter(group.getClassLetter());
            groupPojo.setTeacher(teacherMapper.fromEntity(group.getTeacher()));
            return groupPojo;
        }).toList();
        List<SubjectPojo> subjectsPojo = subjectService.findAll();
        return new GroupsAndSubjectsPojo(groupsPojo, subjectsPojo);
    }
}
