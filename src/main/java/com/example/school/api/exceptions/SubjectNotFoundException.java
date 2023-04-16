package com.example.school.api.exceptions;

public class SubjectNotFoundException extends RuntimeException {
    public String attrName;
    public SubjectNotFoundException(String message) {
        super("Предмет с данным id не найден: " + message);
        attrName = message;
    }
}
