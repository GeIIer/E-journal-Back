package com.example.school.api.controllers;

import com.example.school.api.dto.StudentPojo;
import com.example.school.api.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RequestMapping({"/api/students"})
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/count")
    public long count() {
        return studentService.count();
    }

    @GetMapping("/all")
    public List<StudentPojo> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/all/{groupId}")
    public List<StudentPojo> findAllByGroup(@PathVariable Long groupId) {
        return studentService.findAllByGroup(groupId);
    }

    @PostMapping()
    public StudentPojo createStudent(@RequestBody StudentPojo studentPojo) {
        return studentService.createStudent(studentPojo);
    }

    @PutMapping()
    public StudentPojo updateStudent(@RequestBody StudentPojo dto) {
        return studentService.update(dto);
    }

    @DeleteMapping("/{id}")
    public boolean deleteStudent(@PathVariable Long id) {
        return studentService.deleteById(id);
    }

    @GetMapping("/{id}")
    public StudentPojo findStudentById(@PathVariable Long id) {
        return studentService.findById(id);
    }
}
