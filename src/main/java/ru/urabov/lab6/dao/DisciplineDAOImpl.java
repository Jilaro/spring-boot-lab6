package ru.urabov.lab6.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.urabov.lab6.entity.Discipline;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class DisciplineDAOImpl implements DisciplineDAO {

    // EntityManager выполняет запросы к базе через Hibernate
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Discipline> findAll() {
        // JPQL-запрос обращается к классу Discipline, а не к таблице
        return entityManager.createQuery("from Discipline order by id", Discipline.class).getResultList();
    }

    @Override
    public Optional<Discipline> findById(int id) {
        return Optional.ofNullable(entityManager.find(Discipline.class, id));
    }

    @Override
    public Discipline save(Discipline discipline) {
        // merge вставляет запись, если id = null, и обновляет, если запись с таким id есть
        Discipline saved = entityManager.merge(discipline);
        log.info("Сохранено: {}", saved);
        return saved;
    }

    @Override
    public boolean deleteById(int id) {
        Discipline discipline = entityManager.find(Discipline.class, id);
        if (discipline == null) {
            return false;
        }
        entityManager.remove(discipline);
        log.info("Удалено: {}", discipline);
        return true;
    }
}
