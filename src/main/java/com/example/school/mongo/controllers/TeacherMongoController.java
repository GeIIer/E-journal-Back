package com.example.school.mongo.controllers;

import com.example.school.mongo.models.Teacher;
import com.example.school.mongo.services.TeacherMongoService;
import com.example.school.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mongo/teacher")
@Tag(name = "Контроллер для Teacher Mongo")
public class TeacherMongoController {
    private final TeacherMongoService teacherMongoService;

    public TeacherMongoController(TeacherMongoService teacherMongoService) {
        this.teacherMongoService = teacherMongoService;
    }

    @GetMapping()
    public List<Teacher> getAll() {
        return teacherMongoService.findAll();
    }

    @GetMapping("/{id}")
    public Teacher getById(@PathVariable String id) {
        return teacherMongoService.findById(id);
    }

    @PostMapping()
    public Teacher create(@RequestBody Teacher teacher) {
        return teacherMongoService.save(teacher);
    }

    @PutMapping()
    public Teacher update(@RequestBody Teacher teacher) {
        return teacherMongoService.update(teacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseUtil> deleteById(@PathVariable String id) {
        return teacherMongoService.deleteById(id);
    }

    @GetMapping("/email")
    public List<Teacher> getByEmail(@RequestParam String email) {
        return teacherMongoService.findByEmail(email);
    }

    @GetMapping("/subject")
    public List<Teacher> getBySubjectName(@RequestParam String subjectName) {
        return teacherMongoService.findBySubjectName(subjectName);
    }

    @GetMapping("/salary")
    public List<Teacher> getBySalary(@RequestParam Double salary) {
        return teacherMongoService.findBySalary(salary);
    }
}
