package com.example.school.api.dto.broker;

import lombok.Builder;

public record Message (
        String id,
        String content,
        String timestamp
){
    @Builder
    public Message {
    }
}