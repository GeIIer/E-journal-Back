package com.example.school.clikhouse.services;

import com.example.school.clikhouse.dto.CollapseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClickCollapseServiceImpl implements ClickCollapseService {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ClickCollapseServiceImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public ResponseEntity<String> create(CollapseDto obj) {
        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        if (obj != null) {
            if (obj.getUserId() != null) {
                parameterMap.addValue("userId", obj.getUserId());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userId cannot be blank");
            }
            if (obj.getSearchPhrase() != null && !obj.getSearchPhrase().isBlank()) {
                parameterMap.addValue("searchPhrase", obj.getSearchPhrase());
            }
            else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "searchPhrase cannot be blank");
            }
            if (obj.getSessionDuration() != null && obj.getSessionDuration() > 0) {
                parameterMap.addValue("sessionDuration", obj.getSessionDuration());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "sessionDuration cannot be smaller then 0");
            }
            if (obj.getSign() != null && (obj.getSign().equals(1) || obj.getSign().equals(-1))) {
                parameterMap.addValue("sign", obj.getSign());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "sign must be 1 or -1");
            }
        }
        String query = String.join(
                System.lineSeparator(),
                """
                        INSERT INTO journal.collaps(userId, searchPhrase, sessionDuration, sign)
                        VALUES(:userId, :searchPhrase, :sessionDuration, :sign)
                        """
        );
        if (namedParameterJdbcTemplate.update(query, parameterMap) > 0) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<CollapseDto> findBySearchPhrase(String searchPhrase) {
        if (searchPhrase == null || searchPhrase.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id cannot be null");
        }
        String query = String.join(
                System.lineSeparator(),
                """
                        SELECT
                        	c.userId,
                        	c.searchPhrase,
                        	sum(c.sessionDuration * c.sign) AS "sessionDuration"
                        FROM journal.collaps c
                        WHERE c.searchPhrase = :searchPhrase
                        GROUP BY
                            c.userId,
                            c.searchPhrase
                        HAVING sessionDuration > 0
                        """
        );
        return namedParameterJdbcTemplate.query(query, new MapSqlParameterSource("searchPhrase", searchPhrase), new BeanPropertyRowMapper<>(CollapseDto.class));
    }
}
