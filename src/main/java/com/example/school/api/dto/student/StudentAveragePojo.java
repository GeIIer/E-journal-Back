package com.example.school.api.dto.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StudentAveragePojo extends StudentPojo {
    private String subjectName;
    private BigDecimal average;

    public StudentAveragePojo(Long id,
                              String firstname, String lastname,
                              String email, Long groupId, String subjectName, BigDecimal average) {
        super(id, firstname, lastname, email, groupId);
        this.subjectName = subjectName;
        this.average = average;
    }
}
