package com.example.school.api.services;

import com.example.school.api.dto.SubjectPojo;
import com.example.school.api.entities.SubjectEntity;
import com.example.school.api.exceptions.SubjectNotFoundException;
import com.example.school.api.mapper.SubjectMapper;
import com.example.school.api.repositories.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    private final SubjectMapper subjectMapper;
    public List<SubjectPojo> findAll() {
        return subjectRepository.findAll()
                .stream()
                .map(subjectMapper::fromEntity)
                .toList();
    }

    public SubjectPojo findById(Long id) {
        Optional<SubjectEntity> subjectOptional = subjectRepository.findById(id);
        return subjectOptional.map(subjectMapper::fromEntity).orElseThrow(
                () -> new SubjectNotFoundException(id.toString()));
    }

    public SubjectPojo createSubject(SubjectPojo subjectPojo) {
        SubjectEntity subject = subjectMapper.toEntity(subjectPojo);
        return subjectMapper.fromEntity(subjectRepository.save(subject));
    }

    public boolean deleteById(Long id) {
        try {
            subjectRepository.deleteById(id);
        } catch (IllegalArgumentException ex) {
            return false;
        }
        return true;
    }
}
