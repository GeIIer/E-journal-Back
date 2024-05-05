package com.example.school.redis.services;

import com.example.school.redis.data.User;
import com.example.school.redis.repositories.UserRepository;
import com.example.school.utils.ResponseUtil;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<ResponseUtil> deleteById(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, "User deleted"));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    @Override
    public User update(User user) {
        if (userRepository.existsById(user.getId())) {
            return userRepository.save(user);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    @Override
    public Map<String, String> findAllUserNameMap() {
        Map<String, String> result = new HashMap<>();
        userRepository.findAll().forEach(user -> result.put(user.getId(), user.getName()));
        return result;
    }

    @Override
    public Set<User> findAllByNameSet(String name) {
        if (name != null && !name.isBlank()) {
            Set<User> result = new HashSet<>();
            User user = new User();
            user.setName(name);
            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withIgnorePaths("name")
                    .withIncludeNullValues();
            Example<User> example = Example.of(user, matcher);
            userRepository.findAll(example).forEach(result::add);
            return result;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name cannot be empty");
    }
}
