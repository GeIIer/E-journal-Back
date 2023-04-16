package com.example.school.api.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupPojo {
    private Long id;
    private int classNumber;
    private char classLetter;
    private Long teacherId;
    private List<StudentPojo> listStudents;
}
