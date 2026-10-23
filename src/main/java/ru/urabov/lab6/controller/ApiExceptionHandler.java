package ru.urabov.lab6.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.urabov.lab6.dto.OperationResult;

import java.util.stream.Collectors;

/**
 * Ошибки запросов для всех контроллеров возвращаются в формате OperationResult.
 */
@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    // Тело запроса не прошло проверку аннотаций @NotBlank, @Size, @Min и т. д.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<OperationResult<Void>> handleValidation(MethodArgumentNotValidException e) {
        String errors = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + " – " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));
        log.error("Ошибка валидации: {}", errors);
        return ResponseEntity.badRequest().body(OperationResult.fail("Ошибка валидации: " + errors));
    }

    // Некорректный JSON
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<OperationResult<Void>> handleNotReadable(HttpMessageNotReadableException e) {
        log.error("Не удалось прочитать тело запроса: {}", e.getMostSpecificCause().getMessage());
        return ResponseEntity.badRequest().body(OperationResult.fail("Некорректный JSON в теле запроса"));
    }

    // В адресе вместо числового id передан текст, например /api/students/abc
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<OperationResult<Void>> handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        log.error("Неверный параметр {}: {}", e.getName(), e.getValue());
        return ResponseEntity.badRequest().body(OperationResult.fail("Параметр " + e.getName() + " должен быть числом"));
    }

    // Любая другая ошибка, например недоступна база данных
    @ExceptionHandler(Exception.class)
    public ResponseEntity<OperationResult<Void>> handleOther(Exception e) {
        // Ошибки самого Spring MVC (неизвестный адрес, неверный HTTP-метод) сохраняют свой статус: 404, 405
        if (e instanceof ErrorResponse errorResponse) {
            log.error("Ошибка запроса: {}", e.getMessage());
            return ResponseEntity.status(errorResponse.getStatusCode())
                    .body(OperationResult.fail("Ошибка запроса: " + e.getMessage()));
        }
        log.error("Непредвиденная ошибка", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(OperationResult.fail("Произошла непредвиденная ошибка"));
    }
}
