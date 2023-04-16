package com.example.school.api.repositories;

import com.example.school.api.entities.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    Optional<GroupEntity> findById(Long id);

    Optional<GroupEntity> findByNameContainingIgnoreCase(String name);
}