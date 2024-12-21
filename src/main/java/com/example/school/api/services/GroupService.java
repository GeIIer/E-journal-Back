package com.example.school.api.services;

import com.example.school.api.dto.SubjectPojo;
import com.example.school.api.dto.group.GroupPojo;
import com.example.school.api.dto.group.GroupWithoutStudentsPojo;
import com.example.school.api.dto.group.GroupsAndSubjectsPojo;
import com.example.school.api.entities.GroupEntity;
import com.example.school.api.entities.TeacherEntity;
import com.example.school.api.exceptions.GroupNotFoundException;
import com.example.school.api.exceptions.TeacherNotFoundException;
import com.example.school.api.mapper.BaseMapper;
import com.example.school.api.mapper.TeacherMapper;
import com.example.school.api.repositories.BaseRepository;
import com.example.school.api.repositories.GroupRepository;
import com.example.school.api.repositories.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService extends BaseEntityService<GroupEntity, GroupPojo> {
    private final SubjectService subjectService;
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    public GroupService(BaseRepository<GroupEntity> repository,
                        TeacherRepository teacherRepository,
                        BaseMapper<GroupEntity, GroupPojo> mapper,
                        SubjectService subjectService,
                        TeacherMapper teacherMapper) {
        super(repository, mapper);
        this.teacherRepository = teacherRepository;
        this.subjectService = subjectService;
        this.teacherMapper = teacherMapper;
    }

    @Transactional(readOnly = true)
    public GroupPojo findByName(Character letter) {
        Optional<GroupEntity> group = ((GroupRepository) repository).findByClassLetter(letter);
        return group.map(mapper::fromEntity).orElseThrow(
                () -> new GroupNotFoundException(letter.toString()));
    }

    @Override
    @Transactional
    public GroupPojo create(GroupPojo pojo) {
        if (pojo.getTeacher() == null) {
            throw new TeacherNotFoundException("null");
        }
        Long teacherId = pojo.getTeacher().getId();
        TeacherEntity teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException(String.valueOf(teacherId)));
        GroupEntity entity = mapper.toEntity(pojo);
        entity.setTeacher(teacher);
        return mapper.fromEntity(repository.save(entity));
    }

    @Transactional
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
