package com.example.school.api.repositories;

import com.example.school.api.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    List<StudentEntity> findAllByFirstnameContainingIgnoreCaseAndLastnameEndingWithIgnoreCase(String firstName, String lastName);

    @Query(
            value = "SELECT a.id, email, firstname, lastname, password, role, st.group_id FROM accounts a INNER JOIN students st ON a.id = st.id WHERE st.group_id = :groupId",
            nativeQuery = true)
    List<StudentEntity> findAllByGroup(Long groupId);
}