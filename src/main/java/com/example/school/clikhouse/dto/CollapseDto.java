package com.example.school.clikhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollapseDto {
    private UUID userId;
    private String searchPhrase;
    private Integer sessionDuration;
    private Integer sign;
}
