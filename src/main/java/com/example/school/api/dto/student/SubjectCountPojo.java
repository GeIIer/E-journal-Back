package com.example.school.api.dto.student;

import com.example.school.api.dto.SubjectPojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SubjectCountPojo extends SubjectPojo {
    private Long count;

    public SubjectCountPojo(Long id, String subjectName, Integer studyHours, Integer checkpoints, Long count) {
        super(id, subjectName, studyHours, checkpoints);
        this.count = count;
    }
}
