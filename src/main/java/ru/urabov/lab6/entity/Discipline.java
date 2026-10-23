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
 * Сущность «Учебная дисциплина», соответствует таблице DISCIPLINES.
 */
@Entity
@Table(name = "DISCIPLINES")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Discipline {

    /**
     * Идентификатор, генерируется базой данных (AUTO_INCREMENT)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Название дисциплины, обязательное, до 50 символов
     */
    @NotBlank
    @Size(max = 50)
    @Column(name = "name")
    private String name;

    /**
     * Преподаватель, до 50 символов
     */
    @Size(max = 50)
    @Column(name = "teacher")
    private String teacher;

    /**
     * Объём дисциплины в академических часах
     */
    @Min(1)
    @Max(1000)
    @Column(name = "hours")
    private Integer hours;

    /**
     * Семестр, в котором изучается дисциплина
     */
    @Min(1)
    @Max(12)
    @Column(name = "semester")
    private Integer semester;
}
