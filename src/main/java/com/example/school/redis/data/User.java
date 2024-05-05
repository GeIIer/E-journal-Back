package com.example.school.redis.data;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "User")
public class User {
    @Id
    private String id;
    @Indexed
    private String email;
    private String name;
    private List<Address> address;
}
