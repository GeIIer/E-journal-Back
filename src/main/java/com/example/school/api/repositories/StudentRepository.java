package com.example.school.api.repositories;

import com.example.school.api.entities.StudentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends BaseRepository<StudentEntity> {
    List<StudentEntity> findAllByGroup_Id(Long groupId);
}