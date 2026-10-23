# spring-boot-lab6

Лабораторная работа №6 по дисциплине «Технологии программирования».
Выполнил: Урабов Николай Михайлович, группа РИЗ-250213у.

REST-сервис с базой данных H2: CRUD для студентов и учебных дисциплин.
Spring Boot 4.1.2, Java 25, Maven, Spring Data JPA (Hibernate), H2, Lombok, Validation.

Слои: `controller` → `service` → `dao` → `entity`.

## Запуск

Запустить `SpringBootLab6Application`. База создаётся в папке `testdb` проекта,
таблицы `STUDENTS` и `DISCIPLINES` – скриптом `schema.sql`. Чтобы начать с пустой базы,
остановите приложение и удалите папку `testdb`.

## Методы

| HTTP | URL | Операция |
|------|-----|----------|
| GET | `/api/students` | все студенты |
| GET | `/api/students/{id}` | один студент |
| POST | `/api/students` | добавить студента |
| PUT | `/api/students` | изменить студента (id в теле) |
| DELETE | `/api/students/{id}` | удалить студента |

Для учебных дисциплин те же методы по адресу `/api/disciplines`.

Ответ всегда в формате:

```json
{ "success": true, "message": "Студент найден", "timestamp": "...", "data": { ... } }
```

## Postman

Коллекция `postman/lab6.postman_collection.json`, запросы выполнять по порядку на пустой базе.
