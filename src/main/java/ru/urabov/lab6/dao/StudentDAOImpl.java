package ru.urabov.lab6.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.urabov.lab6.entity.Student;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class StudentDAOImpl implements StudentDAO {

    // EntityManager выполняет запросы к базе через Hibernate
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> findAll() {
        // JPQL-запрос обращается к классу Student, а не к таблице
        return entityManager.createQuery("from Student order by id", Student.class).getResultList();
    }

    @Override
    public Optional<Student> findById(int id) {
        return Optional.ofNullable(entityManager.find(Student.class, id));
    }

    @Override
    public Student save(Student student) {
        // merge вставляет запись, если id = null, и обновляет, если запись с таким id есть
        Student saved = entityManager.merge(student);
        log.info("Сохранено: {}", saved);
        return saved;
    }

    @Override
    public boolean deleteById(int id) {
        Student student = entityManager.find(Student.class, id);
        if (student == null) {
            return false;
        }
        entityManager.remove(student);
        log.info("Удалено: {}", student);
        return true;
    }
}
