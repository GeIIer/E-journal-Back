package com.example.school.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectPojo {
    private Long id;
    private String subjectName;
    private Integer studyHours;
    private Integer checkpoints;
}
