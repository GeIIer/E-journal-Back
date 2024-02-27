package com.example.school.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordPojo {

    private Long id;

    private Long student;

    private Date date;

    private Long subject;

    private Integer result;

    private Boolean present;
}
