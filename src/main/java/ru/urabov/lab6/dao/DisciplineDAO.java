package ru.urabov.lab6.dao;

import ru.urabov.lab6.entity.Discipline;

import java.util.List;
import java.util.Optional;

/**
 * Доступ к таблице DISCIPLINES.
 */
public interface DisciplineDAO {

    List<Discipline> findAll();

    Optional<Discipline> findById(int id);

    Discipline save(Discipline discipline);

    boolean deleteById(int id);
}
