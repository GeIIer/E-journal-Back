package com.example.school.api.services;

import com.example.school.api.dto.GroupPojo;
import com.example.school.api.entities.GroupEntity;
import com.example.school.api.exceptions.GroupNotFoundException;
import com.example.school.api.mapper.GroupMapper;
import com.example.school.api.repositories.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    private final GroupMapper groupMapper;

    public List<GroupPojo> findAll() {
        return groupRepository.findAll()
                .stream()
                .map(groupMapper::fromEntity)
                .toList();
    }

    public GroupPojo findByName(String name) {
        Optional<GroupEntity> group = groupRepository.findByNameContainingIgnoreCase(name);
        return group.map(groupMapper::fromEntity).orElseThrow(
                () -> new GroupNotFoundException(name));
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
}
