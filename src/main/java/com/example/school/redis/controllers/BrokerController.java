package com.example.school.redis.controllers;

import com.example.school.redis.services.MessageService;
import com.example.school.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/redis/broker")
@Slf4j
@Tag(name = "Контроллер для Broker Redis")
public class BrokerController {
    private final MessageService messageService;

    public BrokerController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/email")
    public ResponseEntity<ResponseUtil> sendEmail(@RequestParam String msg) {
        try {
            log.info("Sending email");
            Long count = messageService.publishMessage(msg);
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, "Check email " + count));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }
}
