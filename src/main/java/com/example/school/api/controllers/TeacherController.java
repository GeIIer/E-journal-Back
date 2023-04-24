package com.example.school.api.controllers;

import com.example.school.api.dto.TeacherPojo;
import com.example.school.api.services.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RequestMapping({"/api/teachers"})
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/all")
    public List<TeacherPojo> findAll() {
        return teacherService.findAll();
    }

    @PostMapping()
    public TeacherPojo createTeacher(@RequestBody TeacherPojo teacherPojo) {
        return teacherService.createTeacher(teacherPojo);
    }

    @PutMapping()
    public TeacherPojo updateTeacher(@RequestBody TeacherPojo teacherPojo) {
        return teacherService.createTeacher(teacherPojo);
    }

    @DeleteMapping("/{id}")
    public boolean deleteTeacher(@PathVariable Long id) {
        return teacherService.delete(id);
    }

    @GetMapping("/{id}")
    public TeacherPojo findTeacherById(@PathVariable Long id) {
        return teacherService.findById(id);
    }
}
