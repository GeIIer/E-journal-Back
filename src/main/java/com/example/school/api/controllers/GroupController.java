package com.example.school.api.controllers;

import com.example.school.api.dto.group.GroupPojo;
import com.example.school.api.dto.group.GroupsAndSubjectsPojo;
import com.example.school.api.entities.GroupEntity;
import com.example.school.api.services.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping({"/api/groups"})
@Tag(name = "Контроллер для учебных групп")
public class GroupController extends BaseController<GroupEntity, GroupPojo> {
    protected GroupController(GroupService groupService) {
        super(groupService);
    }

    @Operation(summary = "Получить все учебные группы")
    @Override
    @GetMapping()
    public List<GroupPojo> getAll() {
        return service.findAll();
    }

    @Operation(summary = "Получить список групп и предметов")
    @GetMapping("/subjects")
    public GroupsAndSubjectsPojo findAllGroupsAndSubjects() {
        return ((GroupService) service).findAllGroupsAndSubjects();
    }

    @Operation(summary = "Получить группу по названию")
    @GetMapping("/name/{letter}")
    public GroupPojo findByName(@PathVariable Character letter) {
        return ((GroupService) service).findByName(letter);
    }
}
