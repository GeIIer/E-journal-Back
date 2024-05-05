package com.example.school.redis.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final RedisTemplate<String, String> redisTemplate;
    private final ChannelTopic channelTopic;

    @Override
    public void publishMessage(String msg) {
        redisTemplate.convertAndSend(channelTopic.getTopic(), msg);
    }
}
