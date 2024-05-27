package com.example.school.mongo.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("teachers")
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String firstname;

    private String lastname;

    private String email;

    private Integer experience;

    private Double salary;

    private List<Subject> subjects;
}
