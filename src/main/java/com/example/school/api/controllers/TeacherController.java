package com.example.school.api.controllers;

import com.example.school.api.dto.TeacherPojo;
import com.example.school.api.entities.TeacherEntity;
import com.example.school.api.services.BaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping({"/api/teachers"})
@Tag(name = "Контроллер для преподавателей")
public class TeacherController extends BaseController<TeacherEntity, TeacherPojo> {
    public TeacherController(BaseService<TeacherEntity, TeacherPojo> service) {
        super(service);
    }
}
