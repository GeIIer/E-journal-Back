package com.example.school.api.services.auth;

import com.example.school.api.dto.auth.AccessTokenValue;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface AuthClient {
    Mono<AccessTokenValue> getValueByToken(String jwtToken);
}
