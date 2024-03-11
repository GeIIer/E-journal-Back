package com.example.school.api.dto.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentPojo {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Long groupId;
}
