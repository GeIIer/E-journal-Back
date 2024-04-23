package com.example.school.clikhouse.services;

import com.example.school.clikhouse.dto.CollapseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClickCollapseService {
    ResponseEntity<String> create(CollapseDto obj);
    List<CollapseDto> findBySearchPhrase(String searchPhrase);
}
