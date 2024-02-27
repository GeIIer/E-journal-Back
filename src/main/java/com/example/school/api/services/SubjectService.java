package com.example.school.api.services;

import com.example.school.api.dto.SubjectPojo;
import com.example.school.api.entities.SubjectEntity;
import com.example.school.api.mapper.BaseMapper;
import com.example.school.api.repositories.BaseRepository;
import org.springframework.stereotype.Service;

@Service
public class SubjectService extends BaseEntityService<SubjectEntity, SubjectPojo> {
    public SubjectService(BaseRepository<SubjectEntity> repository,
                          BaseMapper<SubjectEntity, SubjectPojo> mapper) {
        super(repository, mapper);
    }
}
