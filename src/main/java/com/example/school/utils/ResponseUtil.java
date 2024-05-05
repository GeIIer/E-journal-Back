package com.example.school.utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor
public class ResponseUtil {
    private int code;
    private String msg;

    public ResponseUtil(HttpStatus code, String msg) {
        this.code = code.value();
        this.msg = msg;
    }
}
