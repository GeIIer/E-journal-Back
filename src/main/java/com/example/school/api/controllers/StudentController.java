package com.example.school.api.controllers;

import com.example.school.api.dto.student.StudentAveragePojo;
import com.example.school.api.dto.student.StudentOrderPojo;
import com.example.school.api.dto.student.StudentPojo;
import com.example.school.api.entities.StudentEntity;
import com.example.school.api.services.BaseService;
import com.example.school.api.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/students"})
@Tag(name = "Контроллер для студентов")
public class StudentController extends BaseController<StudentEntity, StudentPojo> {
    public StudentController(BaseService<StudentEntity, StudentPojo> service) {
        super(service);
    }

    @Operation(summary = "Получить общее количество учеников")
    @GetMapping("/count")
    public Long count() {
        return ((StudentService) service).count();
    }

    @Operation(summary = "Получить список учеников в группе")
    @GetMapping("/group/{groupId}")
    public List<StudentOrderPojo> findAllByGroup(@PathVariable Long groupId) {
        return ((StudentService) service).findAllByGroup(groupId);
    }

    @Operation(summary = "Получить средний бал учеников")
    @GetMapping("/average")
    public List<StudentAveragePojo> getAverageMark() {
        return ((StudentService) service).findAverageMarkByStudent();
    }
}
