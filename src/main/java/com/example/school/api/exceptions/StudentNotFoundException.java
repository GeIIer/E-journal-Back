package com.example.school.api.exceptions;

public class StudentNotFoundException extends RuntimeException {
    public String attrName;
    public StudentNotFoundException(String message) {
        super("Студент с данным id не найден: " + message);
        attrName = message;
    }
}