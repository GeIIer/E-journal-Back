package com.example.school.api.dto;

import com.example.school.api.entities.SubjectEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherPojo {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private List<SubjectPojo> subjects;
    private int experience;
    private double salary;
}
