package com.example.school.api.controllers;

import com.example.school.api.dto.RecordPojo;
import com.example.school.api.dto.RecordWrapper;
import com.example.school.api.services.RecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping({"/api/records"})
@Tag(name = "Контроллер для записей в электронный журнал")
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;

    @Operation(summary = "Получить все оценки группы по Id предмета")
    @GetMapping()
    public Map<Long, ArrayList<RecordPojo>> getRecordsByGroupAndSubject(@RequestParam(name = "groupId") Long groupId,
                                                                        @RequestParam(name = "subjectId") Long subjectId) throws SQLException {
        return recordService.getRecordsByGroupAndSubject(groupId, subjectId);
    }

    @Operation(summary = "Сохранить несколько записей")
    @PostMapping()
    public int[] saveAll(@RequestBody RecordWrapper records) {
        return recordService.saveAll(records.getRecords());
    }
}
