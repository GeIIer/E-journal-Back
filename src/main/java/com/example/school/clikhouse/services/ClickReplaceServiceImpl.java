package com.example.school.clikhouse.services;

import com.example.school.clikhouse.dto.ReplaceDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class ClickReplaceServiceImpl implements ClickReplaceService {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ClickReplaceServiceImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<ReplaceDto> findAll(ReplaceDto searchRequest) {
        StringBuilder whereBuilder = new StringBuilder(" WHERE 1 = 1 ").append(System.lineSeparator());
        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        if (searchRequest != null) {
            if (searchRequest.getId() != null) {
                whereBuilder.append(" AND r.id = :id ").append(System.lineSeparator());
                parameterMap.addValue("id", searchRequest.getId());
            }
            if (searchRequest.getValue() != null && !searchRequest.getValue().isBlank()) {
                whereBuilder.append(" AND UPPER(r.value) ILIKE :value ");
                parameterMap.addValue("value", searchRequest.getValue().toUpperCase() + "%");
            }
        }

        String query = String.join(
                System.lineSeparator(),
                """
                        SELECT
                        	r.id,
                        	r.value,
                        	r.eventTime
                        FROM journal.replacing r
                        """,
                        whereBuilder.toString(),
                        """
                        ORDER BY eventTime DESC
                        """
        );
        return namedParameterJdbcTemplate.query(query, parameterMap, new BeanPropertyRowMapper<>(ReplaceDto.class));
    }

    @Override
    public ReplaceDto findById(UUID id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id cannot be null");
        }
        String query = String.join(
                System.lineSeparator(),
                """
                        SELECT
                        	r.id,
                        	r.value,
                        	r.eventTime
                        FROM journal.replacing r
                        WHERE r.id = :id
                        ORDER BY eventTime DESC
                        LIMIT 1
                        """
        );
        List<ReplaceDto> res = namedParameterJdbcTemplate.query(query, Collections.singletonMap("id", id), new BeanPropertyRowMapper<>(ReplaceDto.class));
        if (res.isEmpty()) {
            throw new EntityNotFoundException("" + id);
        }
        return res.get(0);
    }

    @Override
    public ResponseEntity<String> create(ReplaceDto obj) {
        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        if (obj != null) {
            if (obj.getValue() != null && !obj.getValue().isBlank()) {
                parameterMap.addValue("value", obj.getValue());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Value cannot be blank");
            }
        }
        String query = String.join(
                System.lineSeparator(),
                """
                        INSERT INTO journal.replacing(id, value, eventTime)
                        VALUES(generateUUIDv4(), :value, now())
                        """
        );
        if (namedParameterJdbcTemplate.update(query, parameterMap) > 0) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> update(ReplaceDto obj) {
        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        if (obj != null) {
            if (existsById(obj.getId())) {
                parameterMap.addValue("id", obj.getId());
            }
            else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Запись не найдена");
            }
            if (obj.getValue() != null && !obj.getValue().isBlank()) {
                parameterMap.addValue("value", obj.getValue());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Value cannot be blank");
            }
        }
        String query = String.join(
                System.lineSeparator(),
                """
                        INSERT INTO journal.replacing(id, value, eventTime)
                        VALUES(:id, :value, now())
                        """
        );
        if (namedParameterJdbcTemplate.update(query, parameterMap) > 0) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> deleteById(UUID id) {
        if (!existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Запись не найдена");
        }
        String query = String.join(
                System.lineSeparator(),
                """
                                DELETE FROM journal.replacing
                                WHERE id = :id
                        """
        );
        if (namedParameterJdbcTemplate.update(query, Collections.singletonMap("id", id)) > 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public boolean existsById(UUID id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id cannot be null");
        }
        String query = String.join(
                System.lineSeparator(),
                """
                                SELECT
                                    r.id
                                FROM journal.replacing r
                                WHERE r.id = :id
                        """
        );
        List<ReplaceDto> res = namedParameterJdbcTemplate.query(query, Collections.singletonMap("id", id), new BeanPropertyRowMapper<>(ReplaceDto.class));
        return !res.isEmpty();
    }
}
