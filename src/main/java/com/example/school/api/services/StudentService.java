package com.example.school.api.services;

import com.example.school.api.dto.StudentPojo;
import com.example.school.api.entities.StudentEntity;
import com.example.school.api.exceptions.StudentNotFoundException;
import com.example.school.api.mapper.StudentMapper;
import com.example.school.api.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class StudentService {

    private final StudentMapper studentMapper;

    private final StudentRepository studentRepository;

    public StudentPojo createStudent(StudentPojo studentPojo) {
        StudentEntity student = studentMapper.toEntity(studentPojo);
        return studentMapper.fromEntity(studentRepository.save(student));
    }

    public long count() {
        return studentRepository.count();
    }

    public List<StudentPojo> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::fromEntity)
                .toList();
    }

    public StudentPojo update(StudentPojo dto) {
        return studentMapper.fromEntity(studentRepository.save(studentMapper.toEntity(dto)));
    }

    public boolean deleteById(Long id) {
        try {
            studentRepository.deleteById(id);
        } catch (IllegalArgumentException ex) {
            return false;
        }
        return true;
    }

    public StudentPojo findById(Long id) {
        Optional<StudentEntity> studentOptional = studentRepository.findById(id);
        return studentOptional.map(studentMapper::fromEntity).orElseThrow(
                () -> new StudentNotFoundException(id.toString()));
    }

    public List<StudentPojo> findAllByGroup(Long groupId) {
        return studentRepository.findAllByGroup_Id(groupId)
                .stream()
                .map(studentMapper::fromEntity)
                .toList();
    }
}
