package com.example.school.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecordWrapper {
    List<RecordPojo> records;
}
