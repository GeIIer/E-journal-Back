package com.example.school.api.controllers;

import com.example.school.api.dto.group.GroupPojo;
import com.example.school.api.dto.group.GroupsAndSubjectsPojo;
import com.example.school.api.entities.GroupEntity;
import com.example.school.api.services.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping({"/api/groups"})
public class GroupController extends BaseController<GroupEntity, GroupPojo> {
    protected GroupController(GroupService groupService) {
        super(groupService);
    }

    @GetMapping()
    public List<GroupPojo> findAll() {
        return service.findAll();
    }

    @GetMapping("/subjects")
    public GroupsAndSubjectsPojo findAllGroupsAndSubjects() {
        return ((GroupService) service).findAllGroupsAndSubjects();
    }

    @GetMapping("/name/{letter}")
    public GroupPojo findByName(@PathVariable Character letter) {
        return ((GroupService) service).findByName(letter);
    }

    @GetMapping("/{id}")
    public GroupPojo findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping()
    public GroupPojo createGroup(@RequestBody GroupPojo dto) {
        return service.create(dto);
    }

    @PutMapping()
    public GroupPojo updateGroup(@RequestBody GroupPojo dto) {
        return service.update(dto);
    }
}
