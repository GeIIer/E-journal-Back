package com.example.school.redis.services;

import com.example.school.redis.data.User;
import com.example.school.utils.ResponseUtil;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {
    List<User> findAll();
    User save(User user);
    ResponseEntity<ResponseUtil> deleteById(String id);
    User update(User user);
    Map<String, String> findAllUserNameMap();
    Set<User> findAllByNameSet(String name);
}
