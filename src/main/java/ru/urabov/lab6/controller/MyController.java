package ru.urabov.lab6.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.urabov.lab6.dto.OperationResult;
import ru.urabov.lab6.entity.Student;
import ru.urabov.lab6.service.StudentService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class MyController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<OperationResult<List<Student>>> getAll() {
        List<Student> list = studentService.getAll();
        log.info("GET /api/students: найдено {}", list.size());
        return ResponseEntity.ok(OperationResult.ok("Найдено записей: " + list.size(), list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationResult<Student>> getById(@PathVariable int id) {
        log.info("GET /api/students/{}", id);
        return studentService.getById(id)
                .map(found -> ResponseEntity.ok(OperationResult.ok("Студент найден", found)))
                .orElseGet(() -> notFound(id));
    }

    @PostMapping
    public ResponseEntity<OperationResult<Student>> create(@Valid @RequestBody Student student) {
        log.info("POST /api/students: {}", student);
        Student created = studentService.create(student);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(OperationResult.ok("Студент добавлен, id = " + created.getId(), created));
    }

    @PutMapping
    public ResponseEntity<OperationResult<Student>> update(@Valid @RequestBody Student student) {
        log.info("PUT /api/students: {}", student);
        if (student.getId() == null) {
            return ResponseEntity.badRequest().body(OperationResult.fail("Для изменения нужно указать id"));
        }
        return studentService.update(student)
                .map(updated -> ResponseEntity.ok(OperationResult.ok("Студент изменён", updated)))
                .orElseGet(() -> notFound(student.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OperationResult<Void>> delete(@PathVariable int id) {
        log.info("DELETE /api/students/{}", id);
        if (studentService.delete(id)) {
            return ResponseEntity.ok(OperationResult.ok("Студент с id = " + id + " удален", null));
        }
        return notFound(id);
    }

    private <T> ResponseEntity<OperationResult<T>> notFound(int id) {
        log.warn("Студенты: запись с id = {} не найдена", id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(OperationResult.fail("Студент с id = " + id + " не найден"));
    }
}
