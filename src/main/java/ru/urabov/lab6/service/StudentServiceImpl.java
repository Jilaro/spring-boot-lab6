package ru.urabov.lab6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.urabov.lab6.dao.StudentDAO;
import ru.urabov.lab6.entity.Student;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentDAO studentDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Student> getAll() {
        return studentDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Student> getById(int id) {
        return studentDAO.findById(id);
    }

    @Override
    @Transactional
    public Student create(Student student) {
        // id назначает база, переданный клиентом id игнорируется
        student.setId(null);
        return studentDAO.save(student);
    }

    @Override
    @Transactional
    public Optional<Student> update(Student student) {
        if (student.getId() == null || studentDAO.findById(student.getId()).isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(studentDAO.save(student));
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return studentDAO.deleteById(id);
    }
}
