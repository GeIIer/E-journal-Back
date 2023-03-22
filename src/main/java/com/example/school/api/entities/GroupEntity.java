package com.example.school.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "groups")
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int classNumber;

    private char classLetter;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private TeacherEntity teacher;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StudentEntity> listStudents;

}