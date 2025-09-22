package br.com.thiago.vaila.exception;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ EntityNotFoundException.class })
    public ResponseEntity<Map<String, String>> handleEntityNotFound(EntityNotFoundException entityNotFoundException) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(Map.of(
                "message", entityNotFoundException.getMessage(),
                "timestamp", Instant.now().toString()
            ));
    }

    @ExceptionHandler({ ConstraintViolationException.class, IllegalArgumentException.class })
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(Exception exception) {
        return ResponseEntity
            .badRequest()
            .body(Map.of(
                "message", exception.getMessage(),
                "timestamp", Instant.now().toString()
            ));
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<Map<String, Object>> handleArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> fieldErrors = methodArgumentNotValidException
            .getBindingResult()
            .getFieldErrors()
            .stream()
            .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        return ResponseEntity
            .badRequest()
            .body(Map.of(
                "message", methodArgumentNotValidException.getTitleMessageCode(),
                "object", methodArgumentNotValidException.getObjectName(),
                "field_errors", fieldErrors,
                "timestamp", Instant.now().toString()
            ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleUnhandledException(Exception exception) {
        return ResponseEntity
            .internalServerError()
            .body(Map.of(
                "message", "Unknown error",
                "timestamp", Instant.now().toString()
            ));
    }
}
