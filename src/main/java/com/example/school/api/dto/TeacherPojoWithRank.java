package com.example.school.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherPojoWithRank {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Integer experience;
    private Double salary;
    private Long parentId;
    private Integer rank;
}
