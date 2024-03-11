package com.example.school.api.controllers;

import com.example.school.api.dto.student.SubjectCountPojo;
import com.example.school.api.dto.SubjectPojo;
import com.example.school.api.entities.SubjectEntity;
import com.example.school.api.services.BaseService;
import com.example.school.api.services.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping({"/api/subjects"})
@Tag(name = "Контроллер для предметов")
public class SubjectController extends BaseController<SubjectEntity, SubjectPojo> {
    public SubjectController(BaseService<SubjectEntity, SubjectPojo> service) {
        super(service);
    }

    @Operation(summary = "Получить количество учителей по каждому предмету, зарплата которых выше указанной")
    @GetMapping("/teacher")
    public List<SubjectCountPojo> getCountTeachers(@RequestParam Double salary) {
        return ((SubjectService)service).getCountTeachers(salary);
    }
}
