package ru.urabov.lab6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.urabov.lab6.dao.DisciplineDAO;
import ru.urabov.lab6.entity.Discipline;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DisciplineServiceImpl implements DisciplineService {

    private final DisciplineDAO disciplineDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Discipline> getAll() {
        return disciplineDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Discipline> getById(int id) {
        return disciplineDAO.findById(id);
    }

    @Override
    @Transactional
    public Discipline create(Discipline discipline) {
        // id назначает база, переданный клиентом id игнорируется
        discipline.setId(null);
        return disciplineDAO.save(discipline);
    }

    @Override
    @Transactional
    public Optional<Discipline> update(Discipline discipline) {
        if (discipline.getId() == null || disciplineDAO.findById(discipline.getId()).isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(disciplineDAO.save(discipline));
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return disciplineDAO.deleteById(id);
    }
}
