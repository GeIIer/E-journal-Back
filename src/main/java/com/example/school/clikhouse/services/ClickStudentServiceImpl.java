package com.example.school.clikhouse.services;

import com.example.school.api.exceptions.StudentNotFoundException;
import com.example.school.clikhouse.dto.StudentDto;
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
public class ClickStudentServiceImpl implements ClickStudentService {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ClickStudentServiceImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<StudentDto> findAll(StudentDto searchRequest) {
        StringBuilder whereBuilder = new StringBuilder(" WHERE 1 = 1 ").append(System.lineSeparator());
        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        if (searchRequest != null) {
            if (searchRequest.getId() != null) {
                whereBuilder.append(" AND s.id = :id ").append(System.lineSeparator());
                parameterMap.addValue("id", searchRequest.getId());
            }
            if (searchRequest.getFirstname() != null && !searchRequest.getFirstname().isBlank()) {
                whereBuilder.append(" AND UPPER(s.firstname) ILIKE :firstname ");
                parameterMap.addValue("firstname", searchRequest.getFirstname().toUpperCase() + "%");
            }
            if (searchRequest.getLastname() != null && !searchRequest.getLastname().isBlank()) {
                whereBuilder.append(" AND UPPER(s.lastname) ILIKE :lastname ");
                parameterMap.addValue("lastname", searchRequest.getLastname().toUpperCase() + "%");
            }
            if (searchRequest.getEmail() != null && !searchRequest.getEmail().isBlank()) {
                whereBuilder.append(" AND UPPER(s.email) ILIKE :email ");
                parameterMap.addValue("email", searchRequest.getEmail().toUpperCase() + "%");
            }
        }

        String query = String.join(
                System.lineSeparator(),
                """
                                SELECT
                                    s.id,
                                    s.firstname,
                                    s.lastname,
                                    s.email
                                FROM journal.students s
                        """,
                whereBuilder.toString()
        );
        return namedParameterJdbcTemplate.query(query, parameterMap, new BeanPropertyRowMapper<>(StudentDto.class));
    }

    @Override
    public StudentDto findById(UUID id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id cannot be null");
        }
        String query = String.join(
                System.lineSeparator(),
                """
                                SELECT
                                    s.id,
                                    s.firstname,
                                    s.lastname,
                                    s.email
                                FROM journal.students s
                                WHERE s.id = :id
                        """
        );
        List<StudentDto> res = namedParameterJdbcTemplate.query(query, Collections.singletonMap("id", id), new BeanPropertyRowMapper<>(StudentDto.class));
        if (res.isEmpty()) {
            throw new StudentNotFoundException("" + id);
        }
        return res.get(0);
    }

    @Override
    public ResponseEntity<String> create(StudentDto obj) {
        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        if (obj != null) {
            if (obj.getFirstname() != null && !obj.getFirstname().isBlank()) {
                parameterMap.addValue("firstname", obj.getFirstname());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Firstname cannot be blank");
            }
            if (obj.getLastname() != null && !obj.getLastname().isBlank()) {
                parameterMap.addValue("lastname", obj.getLastname());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lastname cannot be blank");
            }
            if (obj.getEmail() != null && !obj.getEmail().isBlank()) {
                parameterMap.addValue("email", obj.getEmail());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email cannot be blank");
            }
        }
        String query = String.join(
                System.lineSeparator(),
                """
                        INSERT INTO journal.students(id, firstname, lastname, email)
                        VALUES(generateUUIDv4(), :firstname, :lastname, :email)
                        """
        );
        if (namedParameterJdbcTemplate.update(query, parameterMap) > 0) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> update(StudentDto obj) {
        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        if (obj != null) {
            if (existsById(obj.getId())) {
                parameterMap.addValue("id", obj.getId());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Запись не найдена");
            }
            if (obj.getFirstname() != null && !obj.getFirstname().isBlank()) {
                parameterMap.addValue("firstname", obj.getFirstname());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Firstname cannot be blank");
            }
            if (obj.getLastname() != null && !obj.getLastname().isBlank()) {
                parameterMap.addValue("lastname", obj.getLastname());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lastname cannot be blank");
            }
            if (obj.getEmail() != null && !obj.getEmail().isBlank()) {
                parameterMap.addValue("email", obj.getEmail());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email cannot be blank");
            }
        }
        String query = String.join(
                System.lineSeparator(),
                """
                        ALTER TABLE journal.students UPDATE
                        firstname = :firstname,
                        lastname = :lastname,
                        email = :email
                        WHERE id = :id
                        """
        );
        if (namedParameterJdbcTemplate.update(query, parameterMap) > 0) {
            return new ResponseEntity<>(HttpStatus.OK);
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
                                DELETE FROM journal.students
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
                                    s.id
                                FROM journal.students s
                                WHERE s.id = :id
                        """
        );
        List<StudentDto> res = namedParameterJdbcTemplate.query(query, Collections.singletonMap("id", id), new BeanPropertyRowMapper<>(StudentDto.class));
        return !res.isEmpty();
    }
}
