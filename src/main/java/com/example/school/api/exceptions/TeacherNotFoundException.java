package com.example.school.api.exceptions;

public class TeacherNotFoundException extends RuntimeException {
    public String attrName;

    public TeacherNotFoundException(String message) {
        super("Учитель с данным id не найден: " + message);
        attrName = message;
    }
}