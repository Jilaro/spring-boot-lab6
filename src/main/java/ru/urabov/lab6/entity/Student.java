package ru.urabov.lab6.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Сущность «Студент», соответствует таблице STUDENTS.
 */
@Entity
@Table(name = "STUDENTS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    /**
     * Идентификатор, генерируется базой данных (AUTO_INCREMENT)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Имя, до 15 символов
     */
    @NotBlank
    @Size(max = 15)
    @Column(name = "name")
    private String name;

    /**
     * Фамилия, до 25 символов
     */
    @NotBlank
    @Size(max = 25)
    @Column(name = "surname")
    private String surname;

    /**
     * Факультет, до 20 символов
     */
    @Size(max = 20)
    @Column(name = "faculty")
    private String faculty;

    /**
     * Возраст
     */
    @Min(14)
    @Max(100)
    @Column(name = "age")
    private Integer age;
}
