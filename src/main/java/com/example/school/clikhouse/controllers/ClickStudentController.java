package com.example.school.clikhouse.controllers;

import com.example.school.clikhouse.dto.StudentDto;
import com.example.school.clikhouse.services.ClickStudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clickhouse/student")
@Tag(name = "Контроллер для студентов ClickHouse MergeTree")
public class ClickStudentController {
    private final ClickStudentService service;

    public ClickStudentController(ClickStudentService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Получить все сущности")
    public List<StudentDto> getAll(StudentDto searchRequest) {
        return service.findAll(searchRequest);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Получить сущность по Id")
    public StudentDto getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @Operation(summary = "Создать сущность")
    @PostMapping
    public ResponseEntity<String> create(StudentDto obj) {
        return service.create(obj);
    }

    @Operation(summary = "Изменить сущность")
    @PutMapping
    public ResponseEntity<String> updateById(StudentDto obj) {
        return service.update(obj);
    }

    @Operation(summary = "Удалить сущность по Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable UUID id) {
        return service.deleteById(id);
    }
}
