package com.example.school.clikhouse.services;

import com.example.school.clikhouse.dto.StudentDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ClickStudentService {
    List<StudentDto> findAll(StudentDto searchRequest);

    StudentDto findById(UUID id);

    ResponseEntity<String> create(StudentDto obj);

    ResponseEntity<String> update(StudentDto obj);

    ResponseEntity<String> deleteById(UUID id);

    boolean existsById(UUID id);
}
