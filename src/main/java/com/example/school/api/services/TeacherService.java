package com.example.school.api.services;

import com.example.school.api.dto.TeacherPojo;
import com.example.school.api.entities.TeacherEntity;
import com.example.school.api.exceptions.TeacherNotFoundException;
import com.example.school.api.mapper.TeacherMapper;
import com.example.school.api.repositories.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeacherService {
    private final TeacherMapper teacherMapper;

    private final TeacherRepository teacherRepository;

    public List<TeacherPojo> findAll() {
        return teacherRepository.findAll()
                .stream()
                .map(teacherMapper::fromEntity)
                .toList();
    }

    public TeacherPojo createTeacher(TeacherPojo teacherPojo) {
        TeacherEntity entity = teacherMapper.toEntity(teacherPojo);
        return teacherMapper.fromEntity(teacherRepository.save(entity));
    }

    public boolean delete(Long id) {
        try {
            teacherRepository.deleteById(id);
        } catch (IllegalArgumentException ex) {
            return false;
        }
        return true;
    }

    public TeacherPojo findById(Long id) {
        Optional<TeacherEntity> optionalTeacher = teacherRepository.findById(id);
        return optionalTeacher.map(teacherMapper::fromEntity).orElseThrow(
                () -> new TeacherNotFoundException(id.toString()));
    }
}
