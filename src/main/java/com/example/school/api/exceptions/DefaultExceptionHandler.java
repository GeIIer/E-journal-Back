package com.example.school.api.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler({
            StudentNotFoundException.class,
            GroupNotFoundException.class,
            SubjectNotFoundException.class
    })
    public ResponseEntity<ApiError> handleException(Exception ex,
                                                    HttpServletRequest request) {
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDate.now()
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
