package com.example.school.redis.controllers;

import com.example.school.redis.data.User;
import com.example.school.redis.services.UserService;
import com.example.school.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/redis/user")
@Tag(name = "Контроллер для User Redis")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getUsers() {
        return userService.findAll();
    }

    @PostMapping()
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping()
    public User updateUser(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseUtil> deleteUser(@PathVariable String id) {
        return userService.deleteById(id);
    }

    @GetMapping("/map/name")
    public Map<String, String> getUserNameMap() {
        return userService.findAllUserNameMap();
    }

    @GetMapping("/set")
    public Set<User> getSetUser(@RequestParam String name) {
        return userService.findAllByNameSet(name);
    }

    @PostMapping("/list")
    public ResponseEntity<ResponseUtil> addList(
            @RequestParam String key,
            @RequestParam String email) {
        return userService.addList(key, email);
    }

    @GetMapping("/list/{key}")
    public ResponseEntity<ResponseUtil> getFromList(@PathVariable String key) {
        return userService.getFromList(key);
    }

    @PostMapping("/set")
    public ResponseEntity<ResponseUtil> addSet(
            @RequestParam String key,
            @RequestParam String email) {
        return userService.addSet(key, email);
    }

    @GetMapping("/set/{key}")
    public Set<String> getSet(@PathVariable String key) {
        return userService.getSet(key);
    }

    @PostMapping("/map/{key}")
    public ResponseEntity<ResponseUtil> addMap(
            @PathVariable String key,
            @RequestParam Map<String, String> map) {
        return userService.addMap(key, map);
    }

    @GetMapping("/map/{key}")
    public Map<String, String> getMap(@PathVariable String key) {
        return userService.getMap(key);
    }
}
