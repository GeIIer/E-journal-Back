package com.example.school.clikhouse.controllers;

import com.example.school.clikhouse.dto.CollapseDto;
import com.example.school.clikhouse.services.ClickCollapseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clickhouse/collapse")
@Tag(name = "Контроллер для студентов ClickHouse CollapseMergeTree")
public class ClickCollapseController {
    private final ClickCollapseService service;

    public ClickCollapseController(ClickCollapseService service) {
        this.service = service;
    }

    @Operation(summary = "Создать сущность")
    @PostMapping
    public ResponseEntity<String> create(CollapseDto obj) {
        return service.create(obj);
    }

    @GetMapping(value = "/{searchPhrase}")
    @Operation(summary = "Получить сущности по searchPhrase")
    public List<CollapseDto> getBySearchPhrase(@PathVariable String searchPhrase) {
        return service.findBySearchPhrase(searchPhrase);
    }
}
