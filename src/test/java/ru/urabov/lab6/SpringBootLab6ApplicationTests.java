package ru.urabov.lab6;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

// Для теста используется отдельная база в памяти, чтобы не трогать testdb/students
@SpringBootTest
@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:mem:testdb")
class SpringBootLab6ApplicationTests {

    @Test
    void contextLoads() {
    }
}
