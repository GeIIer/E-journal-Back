package com.example.school.api.controllers;

import com.example.school.api.dto.group.GroupPojo;
import com.example.school.api.dto.group.GroupWithoutStudentsPojo;
import com.example.school.api.dto.group.GroupsAndSubjectsPojo;
import com.example.school.api.services.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RequestMapping({"/api/groups"})
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/all")
    public List<GroupWithoutStudentsPojo> findAll() {
        return groupService.findAll();
    }

    @GetMapping("/all/subjects")
    public GroupsAndSubjectsPojo findAllGroupsAndSubjects() {
        return groupService.findAllGroupsAndSubjects();
    }

    @GetMapping("/all/{letter}")
    public GroupPojo findByName(@PathVariable Character letter) {
        return groupService.findByName(letter);
    }

    @GetMapping("/{id}")
    public GroupPojo findById(@PathVariable Long id) {
        return groupService.findById(id);
    }

    @PostMapping()
    public GroupPojo createGroup(@RequestBody GroupPojo dto) {
        return groupService.createGroup(dto);
    }

    @PutMapping()
    public GroupPojo updateGroup(@RequestBody GroupPojo dto) {
        return groupService.updateGroup(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }
}
