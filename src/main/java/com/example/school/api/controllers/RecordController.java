package com.example.school.api.controllers;

import com.example.school.api.dto.RecordPojo;
import com.example.school.api.entities.RecordEntity;
import com.example.school.api.exceptions.StudentNotFoundException;
import com.example.school.api.exceptions.SubjectNotFoundException;
import com.example.school.api.services.BaseService;
import com.example.school.api.services.RecordService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping({"/api/records"})
public class RecordController extends BaseController<RecordEntity, RecordPojo> {
    public RecordController(BaseService<RecordEntity, RecordPojo> service) {
        super(service);
    }

    @GetMapping()
    public Map<Long, ArrayList<RecordPojo>> getRecordsByGroupAndSubject(@RequestParam(name = "groupId") Long groupId,
                                                                        @RequestParam(name = "subjectId") Long subjectId) throws SQLException {
        return ((RecordService) service).getRecordsByGroupAndSubject(groupId, subjectId);
    }

    @PostMapping()
    public int[] saveAll(@RequestBody List<RecordPojo> recordsPojo) {
        try {
            return ((RecordService) service).saveAll(recordsPojo);
        } catch (StudentNotFoundException | SubjectNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
}
