package ru.urabov.lab6.dao;

import ru.urabov.lab6.entity.Student;

import java.util.List;
import java.util.Optional;

/**
 * Доступ к таблице STUDENTS.
 */
public interface StudentDAO {

    List<Student> findAll();

    Optional<Student> findById(int id);

    Student save(Student student);

    boolean deleteById(int id);
}
