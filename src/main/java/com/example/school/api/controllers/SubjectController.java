package com.example.school.api.controllers;

import com.example.school.api.dto.SubjectPojo;
import com.example.school.api.services.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RequestMapping({"/api/subjects"})
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping("/all")
    public List<SubjectPojo> findAll() {
        return subjectService.findAll();
    }

    @GetMapping("/{id}")
    public SubjectPojo findById(@PathVariable Long id) {
        return  subjectService.findById(id);
    }

    @PostMapping()
    public SubjectPojo createSubject(@RequestBody SubjectPojo subjectPojo) {
        return subjectService.createSubject(subjectPojo);
    }

    @DeleteMapping("/{id}")
    public boolean deleteStudent(@PathVariable Long id) {
        return subjectService.deleteById(id);
    }
}
