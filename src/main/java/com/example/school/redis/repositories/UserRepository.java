package com.example.school.redis.repositories;

import com.example.school.redis.data.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String>,
        JpaSpecificationExecutor<User>,
        QueryByExampleExecutor<User> {
}
