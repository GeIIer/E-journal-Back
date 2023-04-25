package com.example.school.api.dto.group;

import com.example.school.api.dto.TeacherPojo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupWithoutStudentsPojo {
    private Long id;
    private int classNumber;
    private char classLetter;
    private TeacherPojo teacher;
}
