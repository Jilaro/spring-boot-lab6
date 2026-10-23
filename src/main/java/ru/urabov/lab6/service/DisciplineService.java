package ru.urabov.lab6.service;

import ru.urabov.lab6.entity.Discipline;

import java.util.List;
import java.util.Optional;

public interface DisciplineService {

    List<Discipline> getAll();

    Optional<Discipline> getById(int id);

    Discipline create(Discipline discipline);

    // Пустой Optional, если записи с таким id нет
    Optional<Discipline> update(Discipline discipline);

    boolean delete(int id);
}
