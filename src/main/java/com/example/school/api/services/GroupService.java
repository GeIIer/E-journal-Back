package com.example.school.api.services;

import com.example.school.api.dto.group.GroupPojo;
import com.example.school.api.dto.group.GroupWithoutStudentsPojo;
import com.example.school.api.dto.group.GroupsAndSubjectsPojo;
import com.example.school.api.dto.SubjectPojo;
import com.example.school.api.entities.GroupEntity;
import com.example.school.api.exceptions.GroupNotFoundException;
import com.example.school.api.mapper.GroupMapper;
import com.example.school.api.mapper.SubjectMapper;
import com.example.school.api.mapper.TeacherMapper;
import com.example.school.api.repositories.GroupRepository;
import com.example.school.api.repositories.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final SubjectRepository subjectRepository;
    private final GroupMapper groupMapper;
    private final SubjectMapper subjectMapper;

    private final TeacherMapper teacherMapper;

    public List<GroupWithoutStudentsPojo> findAll() {
        return groupRepository.findAll()
                .stream()
                .map(groupMapper::fromEntityWithoutStudents)
                .toList();
    }

    public GroupPojo findByName(Character letter) {
        Optional<GroupEntity> group = groupRepository.findByClassLetter(letter);
        return group.map(groupMapper::fromEntity).orElseThrow(
                () -> new GroupNotFoundException(letter.toString()));
    }

    public GroupPojo findById(Long id) {
        Optional<GroupEntity> group = groupRepository.findById(id);
        return group.map(groupMapper::fromEntity).orElseThrow(
                () -> new GroupNotFoundException(id.toString()));
    }

    public GroupPojo createGroup(GroupPojo dto) {
        GroupEntity group = groupMapper.toEntity(dto);
        return groupMapper.fromEntity(groupRepository.save(group));
    }

    public GroupPojo updateGroup(GroupPojo dto) {
        return groupMapper.fromEntity(groupRepository.save(groupMapper.toEntity(dto)));
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }


    public GroupsAndSubjectsPojo findAllGroupsAndSubjects() {
        List<GroupWithoutStudentsPojo> groupsPojo = groupRepository.findAll().stream().map(group -> {
            GroupWithoutStudentsPojo groupPojo = new GroupWithoutStudentsPojo();
            groupPojo.setId(group.getId());
            groupPojo.setClassNumber(group.getClassNumber());
            groupPojo.setClassLetter(group.getClassLetter());
            groupPojo.setTeacher(teacherMapper.fromEntity(group.getTeacher()));
            return groupPojo;
        }).toList();
        List<SubjectPojo> subjectsPojo = subjectRepository.findAll().stream()
                .map(subjectMapper::fromEntity)
                .toList();
        return new GroupsAndSubjectsPojo(groupsPojo, subjectsPojo);
    }
}
