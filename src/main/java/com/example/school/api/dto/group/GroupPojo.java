package com.example.school.api.dto.group;
import com.example.school.api.dto.StudentPojo;
import com.example.school.api.dto.TeacherPojo;
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
    private Integer classNumber;
    private Character classLetter;
    private TeacherPojo teacher;
    private List<StudentPojo> listStudents;
}
