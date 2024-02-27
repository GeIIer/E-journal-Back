package com.example.school.api.controllers;

import com.example.school.api.dto.StudentPojo;
import com.example.school.api.entities.StudentEntity;
import com.example.school.api.services.BaseService;
import com.example.school.api.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping({"/api/students"})
public class StudentController extends BaseController<StudentEntity, StudentPojo> {
    public StudentController(BaseService<StudentEntity, StudentPojo> service) {
        super(service);
    }

    @GetMapping("/count")
    public long count() {
        return ((StudentService) service).count();
    }

    @GetMapping("/group/{groupId}")
    public List<StudentPojo> findAllByGroup(@PathVariable Long groupId) {
        return ((StudentService) service).findAllByGroup(groupId);
    }
}
