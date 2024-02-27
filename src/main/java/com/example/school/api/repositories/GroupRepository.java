package com.example.school.api.repositories;

import com.example.school.api.entities.GroupEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends BaseRepository<GroupEntity> {
    Optional<GroupEntity> findByClassLetter(Character name);
}