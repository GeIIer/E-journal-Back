package com.example.school.api.entities;

import com.example.school.authorization.entities.AccountEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "students")
public class StudentEntity extends AccountEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private GroupEntity group;

}
