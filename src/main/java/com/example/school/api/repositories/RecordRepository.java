package com.example.school.api.repositories;

import com.example.school.api.entities.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RecordRepository extends JpaRepository<RecordEntity, Long> {

    @Override
    void deleteById(Long aLong);
}