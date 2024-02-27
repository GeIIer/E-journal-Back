package com.example.school.api.controllers;

import com.example.school.api.dto.SubjectPojo;
import com.example.school.api.entities.SubjectEntity;
import com.example.school.api.services.BaseService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping({"/api/subjects"})
public class SubjectController extends BaseController<SubjectEntity, SubjectPojo> {

    public SubjectController(BaseService<SubjectEntity, SubjectPojo> service) {
        super(service);
    }
}
