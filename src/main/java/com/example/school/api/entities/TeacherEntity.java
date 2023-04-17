package com.example.school.api.entities;

import com.example.school.authorization.entities.AccountEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "teachers")
public class TeacherEntity extends AccountEntity {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "teachers_subjects",
            joinColumns = @JoinColumn(
                    name = "teacher_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "subject_id", referencedColumnName = "id"
            )
    )
    private List<SubjectEntity> subjects;
    private int experience;
    private double salary;

}
