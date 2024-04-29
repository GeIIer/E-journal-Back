package com.example.school.clikhouse.controllers;

import com.example.school.clikhouse.dto.ReplaceDto;
import com.example.school.clikhouse.services.ClickReplaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clickhouse/replace")
@Tag(name = "Контроллер для студентов ClickHouse ReplaceMergeTree")
public class ClickReplaceController {
    private final ClickReplaceService service;

    public ClickReplaceController(ClickReplaceService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Получить все сущности")
    public List<ReplaceDto> getAll(ReplaceDto searchRequest) {
        return service.findAll(searchRequest);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Получить сущность по Id")
    public ReplaceDto getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @Operation(summary = "Создать сущность")
    @PostMapping
    public ResponseEntity<String> create(@RequestBody ReplaceDto obj) {
        return service.create(obj);
    }

    @Operation(summary = "Изменить сущность")
    @PutMapping
    public ResponseEntity<String> updateById(@RequestBody ReplaceDto obj) {
        return service.update(obj);
    }

    @Operation(summary = "Удалить сущность по Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable UUID id) {
        return service.deleteById(id);
    }
}
