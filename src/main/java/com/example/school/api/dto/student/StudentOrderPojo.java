package com.example.school.api.dto.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StudentOrderPojo extends StudentPojo {
    private Long order;

    public StudentOrderPojo(Long id,
                              String firstname, String lastname,
                              String email, Long groupId, Long order) {
        super(id, firstname, lastname, email, groupId);
        this.order = order;
    }
}
