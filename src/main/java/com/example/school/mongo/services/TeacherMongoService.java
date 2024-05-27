package com.example.school.mongo.services;

import com.example.school.mongo.models.Teacher;
import com.example.school.utils.ResponseUtil;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeacherMongoService {
    List<Teacher> findAll();

    Teacher findById(String id);

    Teacher save(Teacher teacher);

    Teacher update(Teacher teacher);

    ResponseEntity<ResponseUtil> deleteById(String id);

    List<Teacher> findByEmail(String email);

    List<Teacher> findBySubjectName(String subjectName);

    List<Teacher> findBySalary(Double salary);
}
