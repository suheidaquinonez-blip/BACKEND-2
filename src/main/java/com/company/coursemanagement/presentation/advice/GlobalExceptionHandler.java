package com.company.coursemanagement.presentation.advice;

import com.company.coursemanagement.application.dto.response.ErrorResponse;
import com.company.coursemanagement.domain.exception.BusinessException;
import com.company.coursemanagement.domain.exception.ResourceConflictException;
import com.company.coursemanagement.domain.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404 - el recurso (estudiante, curso, matrícula) no existe
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                        "RESOURCE_NOT_FOUND",
                        ex.getMessage(),
                        LocalDateTime.now()
                ));
    }

    // 409 - ya existe algo con ese dato único (email, código de curso, etc)
    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflict(ResourceConflictException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(
                        "RESOURCE_CONFLICT",
                        ex.getMessage(),
                        LocalDateTime.now()
                ));
    }

    // 400 - cualquier otra regla de negocio (ej: curso lleno)
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(BusinessException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(
                        "BUSINESS_ERROR",
                        ex.getMessage(),
                        LocalDateTime.now()
                ));
    }

    // 400 - falló alguna validación de @Valid en un DTO (@NotBlank, @Email, etc)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    // 500 - cualquier error no controlado
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpected(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        "INTERNAL_ERROR",
                        "Unexpected error",
                        LocalDateTime.now()
                ));
    }
}
