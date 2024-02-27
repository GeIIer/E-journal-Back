package com.example.school.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "records")
public class RecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private StudentEntity student;

    @Temporal(value = TemporalType.DATE)
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id", foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private SubjectEntity subject;

    private Integer result;

    @Column(columnDefinition = "boolean default false")
    private Boolean present;
}
