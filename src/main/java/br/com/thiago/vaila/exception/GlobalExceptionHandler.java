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
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ EntityNotFoundException.class })
    public ResponseEntity<Map<String, String>> handleEntityNotFound(EntityNotFoundException entityNotFoundException) {
        log.error(entityNotFoundException.getMessage(), entityNotFoundException);
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(Map.of(
                "message", entityNotFoundException.getMessage(),
                "timestamp", Instant.now().toString()
            ));
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException illegalArgumentException) {
        log.error(illegalArgumentException.getMessage(), illegalArgumentException);
        return ResponseEntity
            .badRequest()
            .body(Map.of(
                "message", illegalArgumentException.getMessage(),
                "timestamp", Instant.now().toString()
            ));
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<Map<String, Object>> handleArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException) {
        log.error(methodArgumentNotValidException.getMessage(), methodArgumentNotValidException);
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
        log.error(exception.getMessage(), exception);
        return ResponseEntity
            .internalServerError()
            .body(Map.of(
                "message", "Unknown error",
                "timestamp", Instant.now().toString()
            ));
    }
}
