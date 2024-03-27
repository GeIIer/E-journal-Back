package com.example.school.api.controllers;

import com.example.school.api.dto.TeacherPojo;
import com.example.school.api.dto.TeacherPojoWithRank;
import com.example.school.api.entities.TeacherEntity;
import com.example.school.api.services.BaseService;
import com.example.school.api.services.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping({"/api/teachers"})
@Tag(name = "Контроллер для преподавателей")
public class TeacherController extends BaseController<TeacherEntity, TeacherPojo> {
    public TeacherController(BaseService<TeacherEntity, TeacherPojo> service) {
        super(service);
    }

    @Operation(summary = "Получить всех учителей по данным предметам")
    @GetMapping("/subjects")
    public List<TeacherPojo> getAllBySubjects(@RequestParam List<Long> subjectsIds) {
        return ((TeacherService)service).findAllBySubjects(subjectsIds);
    }
    @Operation(summary = "Получить всех учителей и упорядочить по рангу")
    @GetMapping("/recourse")
    public List<TeacherPojoWithRank> getAllBySubjects() {
        return ((TeacherService)service).findAllWithRecourse();
    }

}
