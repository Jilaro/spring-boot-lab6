package ru.urabov.lab6.service;

import ru.urabov.lab6.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<Student> getAll();

    Optional<Student> getById(int id);

    Student create(Student student);

    // Пустой Optional, если записи с таким id нет
    Optional<Student> update(Student student);

    boolean delete(int id);
}
