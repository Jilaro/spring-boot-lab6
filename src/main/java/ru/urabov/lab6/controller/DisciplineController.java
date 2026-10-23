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
import ru.urabov.lab6.entity.Discipline;
import ru.urabov.lab6.service.DisciplineService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/disciplines")
@RequiredArgsConstructor
public class DisciplineController {

    private final DisciplineService disciplineService;

    @GetMapping
    public ResponseEntity<OperationResult<List<Discipline>>> getAll() {
        List<Discipline> list = disciplineService.getAll();
        log.info("GET /api/disciplines: найдено {}", list.size());
        return ResponseEntity.ok(OperationResult.ok("Найдено записей: " + list.size(), list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationResult<Discipline>> getById(@PathVariable int id) {
        log.info("GET /api/disciplines/{}", id);
        return disciplineService.getById(id)
                .map(found -> ResponseEntity.ok(OperationResult.ok("Дисциплина найдена", found)))
                .orElseGet(() -> notFound(id));
    }

    @PostMapping
    public ResponseEntity<OperationResult<Discipline>> create(@Valid @RequestBody Discipline discipline) {
        log.info("POST /api/disciplines: {}", discipline);
        Discipline created = disciplineService.create(discipline);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(OperationResult.ok("Дисциплина добавлена, id = " + created.getId(), created));
    }

    @PutMapping
    public ResponseEntity<OperationResult<Discipline>> update(@Valid @RequestBody Discipline discipline) {
        log.info("PUT /api/disciplines: {}", discipline);
        if (discipline.getId() == null) {
            return ResponseEntity.badRequest().body(OperationResult.fail("Для изменения нужно указать id"));
        }
        return disciplineService.update(discipline)
                .map(updated -> ResponseEntity.ok(OperationResult.ok("Дисциплина изменена", updated)))
                .orElseGet(() -> notFound(discipline.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OperationResult<Void>> delete(@PathVariable int id) {
        log.info("DELETE /api/disciplines/{}", id);
        if (disciplineService.delete(id)) {
            return ResponseEntity.ok(OperationResult.ok("Дисциплина с id = " + id + " удалена", null));
        }
        return notFound(id);
    }

    private <T> ResponseEntity<OperationResult<T>> notFound(int id) {
        log.warn("Дисциплины: запись с id = {} не найдена", id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(OperationResult.fail("Дисциплина с id = " + id + " не найдена"));
    }
}
