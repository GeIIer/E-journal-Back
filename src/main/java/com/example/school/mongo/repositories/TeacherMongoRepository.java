package com.example.school.mongo.repositories;

import com.example.school.mongo.models.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeacherMongoRepository extends MongoRepository<Teacher, String> {
    boolean existsByEmail(String email);

    @Query("{'email' :  {$regex: ?0}}")
    List<Teacher> findByEmailRegex(String email);

    @Query("{'subjects.name' :  ?0}")
    List<Teacher> findBySubjectName(String subjectName);

    @Query("{'salary' :  { $lte : ?0}}")
    List<Teacher> findBySalary(Double salary);

    Optional<Teacher> findByEmail(String email);
}
