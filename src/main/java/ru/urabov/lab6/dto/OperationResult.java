package ru.urabov.lab6.dto;

import java.time.Instant;

/**
 * Единый ответ сервиса: признак успеха, сообщение и данные.
 *
 * @param success   true, если операция выполнена
 * @param message   описание результата операции
 * @param timestamp время формирования ответа
 * @param data      результат операции: объект, список или null
 */
public record OperationResult<T>(boolean success, String message, Instant timestamp, T data) {

    public static <T> OperationResult<T> ok(String message, T data) {
        return new OperationResult<>(true, message, Instant.now(), data);
    }

    public static <T> OperationResult<T> fail(String message) {
        return new OperationResult<>(false, message, Instant.now(), null);
    }
}
