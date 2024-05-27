package com.example.school.mongo.services;

import com.example.school.mongo.models.Teacher;
import com.example.school.mongo.repositories.TeacherMongoRepository;
import com.example.school.utils.ResponseUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherMongoServiceImpl implements TeacherMongoService {
    private final TeacherMongoRepository repository;

    public TeacherMongoServiceImpl(TeacherMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Teacher> findAll() {
        return repository.findAll();
    }

    @Override
    public Teacher findById(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Id must be not null or blank");
        }
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(id)
        );
    }

    @Override
    @Transactional
    public Teacher save(Teacher teacher) {
        if (teacher.getEmail() == null || teacher.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email must be not null or black");
        }
        if (repository.existsByEmail(teacher.getEmail())) {
            throw new IllegalArgumentException("Email must be IDENTITY");
        }
        teacher.setId(null);
        return repository.save(teacher);
    }

    @Override
    @Transactional
    public Teacher update(Teacher teacher) {
        if (teacher.getEmail() == null || teacher.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email must be not null or black");
        }
        if (teacher.getId() == null || teacher.getId().isBlank()) {
            throw new IllegalArgumentException("Id must be not null or black");
        }
        if (!repository.existsById(teacher.getId())) {
            throw new EntityNotFoundException(teacher.getId());
        }
        Optional<Teacher> optionalTeacher = repository.findByEmail(teacher.getEmail());
        if (optionalTeacher.isPresent()) {
            if (!optionalTeacher.get().getId().equals(teacher.getId())) {
                throw new IllegalArgumentException("Email must be IDENTITY");
            }
        }
        return repository.save(teacher);
    }

    @Override
    public ResponseEntity<ResponseUtil> deleteById(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, "Документ удален: " + id));
        }
        throw new EntityNotFoundException(id);
    }

    @Override
    public List<Teacher> findByEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email must be not null or black");
        }
        return repository.findByEmailRegex(email);
    }

    @Override
    public List<Teacher> findBySubjectName(String subjectName) {
        if (subjectName == null || subjectName.isBlank()) {
            throw new IllegalArgumentException("Subject name must be not null or black");
        }
        return repository.findBySubjectName(subjectName);
    }

    @Override
    public List<Teacher> findBySalary(Double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary must be more then 0");
        }
        return repository.findBySalary(salary);
    }
}
