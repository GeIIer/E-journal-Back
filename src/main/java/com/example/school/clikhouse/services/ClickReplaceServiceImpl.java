package com.example.school.clikhouse.services;

import com.example.school.clikhouse.dto.ReplaceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClickReplaceServiceImpl implements ClickReplaceService {

    @Override
    public List<ReplaceDto> findAll(ReplaceDto searchRequest) {
        return List.of();
    }

    @Override
    public ReplaceDto findById(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<String> create(ReplaceDto obj) {
        return null;
    }

    @Override
    public ResponseEntity<String> update(ReplaceDto obj) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteById(UUID id) {
        return null;
    }
}
