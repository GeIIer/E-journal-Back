package com.example.school.clikhouse.services;

import com.example.school.clikhouse.dto.ReplaceDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ClickReplaceService {
    List<ReplaceDto> findAll(ReplaceDto searchRequest);
    ReplaceDto findById(UUID id);
    ResponseEntity<String> create(ReplaceDto obj);
    ResponseEntity<String> update(ReplaceDto obj);
    ResponseEntity<String> deleteById(UUID id);
    boolean existsById(UUID id);
}
